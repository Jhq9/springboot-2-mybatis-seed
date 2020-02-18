package isa.qa.project.core;

import isa.qa.project.enums.ResultCodeEnums;

import static isa.qa.project.enums.ResultCodeEnums.BAD_REQUEST;
import static isa.qa.project.enums.ResultCodeEnums.OK;


/**
 * Result Generator
 *
 * @author May
 * @version 1.0
 * @date 2018/11/21 10:05
 */
public class ResultGenerator {

	public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	/**
	 * HttpStatus is 200(Success) but data is null
	 */
	public static Result genSuccessResult() {
		return new Result()
				.setCode(OK)
				.setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * HttpStatus is 200(Success) and data is not null
	 */
	public static <T> Result genSuccessResult(T data) {
		return new Result()
				.setCode(OK)
				.setMessage(DEFAULT_SUCCESS_MESSAGE)
				.setData(data);
	}

	/**
	 * HttpStatus is 400(failed) and Customize the error message
	 */
	public static Result genFailResult(String message) {
		return new Result()
				.setCode(BAD_REQUEST)
				.setMessage(message);
	}

	/**
	 * Customize the httpStatus code and the error message
	 */
	public static Result genFailResult(ResultCodeEnums resultCodeEnum, String message) {
		return new Result()
				.setCode(resultCodeEnum.code)
				.setMessage(message);
	}
}
