package com.yingjun.ssm.web.goods.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.RpcException;
import com.yingjun.ssm.api.goods.entity.Goods;
import com.yingjun.ssm.api.goods.service.GoodsService;
import com.yingjun.ssm.common.dto.BaseResult;
import com.yingjun.ssm.common.dto.BootStrapTableResult;
import com.yingjun.ssm.common.enums.BizExceptionEnum;
import com.yingjun.ssm.common.model.MailParam;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsService goodsService;

	/**
	 * 摒弃jsp页面通过ajax接口做到真正意义上的前后分离
	 *
	 * @param offset
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/list", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BootStrapTableResult<Goods> list(Integer offset, Integer limit) {
		logger.info("invoke----------/goods/list");
		offset = offset == null ? 0 : offset; 
		limit = limit == null ? 50 : limit; 
		List<Goods> list = goodsService.getGoodsList(offset, limit);
		return new BootStrapTableResult<Goods>(list);
	}

	@RequestMapping(value = "/{goodsId}/buy", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResult<Object> buy(@CookieValue(value = "userPhone", required = false) Long userPhone,
			@Valid Goods goods, BindingResult result, HttpSession httpSession) {
		logger.info("invoke----------/" + goods.getGoodsId() + "/buy userPhone:" + userPhone);
		if (userPhone == null) {
			// return new BaseResult<Object>(false, UserExceptionEnum.INVALID_USER.getMsg());
			userPhone = 18768128888l;
		}
		// Valid 参数验证
		if (result.hasErrors()) {
			String errorInfo = "[" + result.getFieldError().getField() + "]" + result.getFieldError().getDefaultMessage();
			return new BaseResult<Object>(false, errorInfo);
		}
		// 这里纯粹是为了验证集群模式的session共享功能上
		logger.info("lastSessionTime:" + httpSession.getAttribute("sessionTime"));
		httpSession.setAttribute("sessionTime", System.currentTimeMillis());
		try {
			goodsService.buyGoods(userPhone, goods.getGoodsId(), false);
		} catch (RpcException e) {
			logger.error(e.getMessage());
			return new BaseResult<Object>(false, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BaseResult<Object>(false, BizExceptionEnum.INNER_ERROR.getMsg());
		}
		return new BaseResult<Object>(true, null);
	}

	@RequestMapping(value = "/{goodsId}/testDistributedTransaction", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResult<Object> testDistributedTransaction(@Valid Goods goods, BindingResult result) {
		logger.info("invoke----------/goods/testDistributedTransaction");
		// Valid 参数验证
		if (result.hasErrors()) {
			String errorInfo = "[" + result.getFieldError().getField() + "]" + result.getFieldError().getDefaultMessage();
			return new BaseResult<Object>(false, errorInfo);
		}
		try {
			goodsService.testDistributedTransaction(goods.getGoodsId());
		} catch (RpcException e) {
			logger.error(e.getMessage());
			return new BaseResult<Object>(false, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BaseResult<Object>(false, BizExceptionEnum.INNER_ERROR.getMsg());
		}
		return new BaseResult<Object>(true, null);
	}

	@RequestMapping(value = "mail")
	@ResponseBody
	public String testMail() {
		// 邮件发送
		MailParam mail = new MailParam();
		mail.setTo("120764874@qq.com");
		mail.setSubject("订单确认");
		mail.setContent("你下单成功！");
		goodsService.testSendMail(mail);
		return "success";
	}
}
