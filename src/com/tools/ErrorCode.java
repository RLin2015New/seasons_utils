package com.tools;

/**
 * @projectName qipai_niuyouba_login
 * @fileName ErrorCode.java
 * @description
 * @author lifl
 * @time 2017下午5:51:38
 *
 */

public enum ErrorCode {

	code_common_versionUpdate(2017, "有新的更新内容，请退出重登"), //
	code_register_registred(1, "手机号已存在"), //
	code_common_authorerr(4, "验证不通过"), //
	code_register_phoneerr(-101, "手机号不符合规范"), //
	code_register_phoneregistered(-102, "手机号已被注册"), //
	code_register_checkcodeerr(-103, "验证码错误或已失效，请重新验证"), //
	code_register_checknophone(-104, "手机号不存在"), //
	code_register_errparams(-1000, "参数错误"), //
	code_register_errname(-1001, "用户名只允许2-6个有效字"), //
	code_register_errnamecode(-1002, "用户名只允许是数字、字母或中文"), //
	code_register_samename(-1003, "用户名已存在"), //
	code_register_sensitivewords(-1004, "包含敏感字"), //

	// 俱乐部
	code_club_reachMaxClubNum(-1101, "已达可加入或创建的俱乐部上限"), //
	code_club_clubFull(-1102, "俱乐部成员已满"), //
	code_club_requested(-1103, "已经申请过加入该俱乐部"), //
	code_club_notFound(-1104, "没有找到俱乐部"), //
	code_club_illegalClubName(-1105, "俱乐部名字不符合规范"), //
	code_club_illegalClubDescription(-1106, "描述信息不符合规范"), //
	code_club_sameName(-1107, "俱乐部名字已存在"), //
	code_club_errorLevel(-1108, "升级俱乐部的等级必须大于当前等级"), //
	code_club_created(-1109, "已创建过俱乐部，不能再次创建"), //
	code_club_notAllowQuit(-1110, "创建人不允许退出或被踢出俱乐部"), //
	code_club_notExist(-1111, "俱乐部不存在"), //
	code_club_sensitive(-1112, "俱乐部名字中包含敏感字"), //
	code_club_notinclude(-1113, "成员不存在"), //
	code_club_noClubMember(-1114, "该成员不是俱乐部成员"), //
	code_club_notMember(-1115, "您不是该俱乐部成员"), //
	// 联盟
	code_union_reachMaxUnionNum(-1201, "已达到可加入或创建联盟的数量"), //
	code_union_unionFull(-1202, "联盟成员已满"), //
	code_union_requested(-1203, "已经申请过加入该联盟"), //
	code_union_illegalUnionName(-1204, "联盟名字不符合规范"), //
	code_union_sameName(-1205, "联盟名字已存在"), //
	code_union_notAllowQuit(-1206, "创建俱乐部不允许退出或被踢出联盟"), //
	code_union_notExist(-1207, "联盟不存在"), //
	code_union_sensitive(-1208, "联盟不存在"), //
	code_union_created(-1209, "已创建过联盟"), //
	code_union_notMember(-1210, "俱乐部不是该联盟成员"), //
	// 服务器通用消息
	code_common_noright(-2010, "没有权限"), //
	code_common_illegal(-2011, "参数错误"), //
	code_common_servereErr(-2012, "服务器错误"), //
	code_common_databaseErr(-2013, "数据库错误"), //
	code_common_norecord(-2014, "没有找到对应记录"), //
	code_common_signErr(-2015, "签名错误"), //
	code_common_illegalParams(-2016, "参数错误"), //
	code_common_reqError(-2017, "错误的请求"), //
	code_common_notYet(-2018, "暂未实现"), //
	code_common_notEnoughDiamond(-2019, "钻石不足"), //
	code_common_unknowErr(-2020, "未知错误"), //
	code_common_errorPage(-2021, "分页参数错误"), // 分页参数错误
	code_common_errorNumber(-2022, "必须为数字"), // 分页参数错误
	code_common_noUser(-2023, "用户不存在"), // 用户不存在
	code_common_noMoney(-2024, "金币不够"), //
	code_common_payError(-2025, "金币相关异常"), //
	code_common_noRoom(-2026, "房间不存在"), //
	code_common_serverErr(-2027, "服务器异常"), //
	code_common_notBringIn(-2028, "玩家已取消带入或离开房间"), //
	// 商场消息
	code_shop_noitem(-3010, "没有该项商品"),

	// 登陆消息
	code_login_errorAccount(-4010, "账号不存在"), //
	code_login_errorPassword(-4011, "密码错误"),

	// 兑换码
	code_cdk_isEmpty(-5010, "兑换码为空"), //
	code_cdk_cdkUsed(-5011, "该兑换码已使用"), //
	code_cdk_notExist(-5012, "该兑换码不存在"), //
	code_cdk_used(-5013, "您已兑换过该类兑换码"), //
	code_cdk_error(-5014, "非法兑换码"), //
	code_cdk_dberror(-5015, "兑换出错"), //
	code_cdk_noRight(-5016, "没有领取资格"), //
	code_cdk_overTime(-5017, "兑换码已过期"), //

	// 绑定用户
	code_bind_notBind(-5100, ""), // 这个前台有功用，不要随意修改
	code_bind_binded(-5101, "用户已经绑定过"), //
	code_bind_errorBindCode(-5102, "错误的邀请码"), //
	code_bind_bindNotExist(-5103, "邀请码用户不存在"), //
	code_bind_bindRecordNotExist(-5104, "邀请记录不存在"), //
	code_bind_rewarded(-5105, "已经领取过奖励"), //
	code_bind_errorBind01(-5106, "不允许绑定自己"), //

	// 游戏
	code_game_emptyRoom(-6001, "不存在的房间请求"), //

	;

	private int code;
	private String msg;

	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg(Object... o) {
		String result = msg;
		if (o == null || o.length == 0) {
			return result;
		}
		for (int i = 0; i < o.length; i++) {
			if (result.contains("{" + i + "}")) {
				result = result.replace("{" + i + "}", String.valueOf(o[i]));
			} else {
				result += (" _" + o[i]);
			}

		}
		return result;
	}

	public int getCode() {
		return code;
	}
}
