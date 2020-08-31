package club.wsn2019.wechatbot.entity

interface IResult

data class SuccessResult(val data: Any? = null, val code: Int = 0) : IResult

data class FailureResult(val message: String? = "OK", val code: Int = 1) : IResult
