const ApiRootUrl = 'http://localhost:8010/';

const API = {
	//根路径
	ApiRootUrl: ApiRootUrl,

	//======================用户相关接口=======================
	//注册：id，name，password，realname，telphone，eamil，cardid
	Register: ApiRootUrl + 'user/register/',
	//登录接口，+this.phoneNumber+'/null/'+this.passWord
	Login: ApiRootUrl + 'user/login/',
	//获取用户信息接口
	GetUser: ApiRootUrl + 'user/searchOne/',
	//获取用户列表接口
	GetUserList: ApiRootUrl + 'user/list/',
	// 修改用户接口,User
	UpdateUser: ApiRootUrl + 'user/update',

	//地区接口，+cityName+"/"+type
	GetRegion: ApiRootUrl + 'region/getRegion/',
	GetNotice: ApiRootUrl + 'notice/getNotice/', //用户通知接口,+username
	AddNotice: ApiRootUrl + 'notice/addNotice', //添加通知接口，notice
	//======================房屋相关接口=======================
	//得到房屋列表接口
	GetHouseList: ApiRootUrl + 'product/searchForPage',
	//删除房屋
	delHouse: ApiRootUrl + 'product/delete',
	//添加房屋接口,house
	AddHouse: ApiRootUrl + 'product/add',
	//修改房屋接口，house
	UpdateHouse: ApiRootUrl + 'product/update',
	//上传图片
	UploadImage: ApiRootUrl + 'util/upload/',
	//回显图片路径
	ShowImage: ApiRootUrl + 'api/upload/resource/imgs/',
	//管理员得到房屋接口
	GetHouseByAdmin: ApiRootUrl + 'product/listByAdmin/',
	//房屋预定的时间列表
	GetHouseTimeList: ApiRootUrl + 'product/timeList/',

	//得到房屋总数量接口,allFilters
	GetHouseTotal: ApiRootUrl + 'house/getHouseTotal',
	GetHouse: ApiRootUrl + 'house/getHouse/', //得到单个房屋接口，+this.$store.state.userInfo.username+'/null'
	// GetHouseTotalByAdmin:ApiRootUrl + 'product/list/',//管理员得到得到房屋数量，+state
	//所有房屋类型
	GetCategory: ApiRootUrl + 'category/list',

	//======================房屋属性相关接口=======================
	//房屋属性查询
	GetProperty: ApiRootUrl + 'property/list',
	AddProperty: ApiRootUrl + 'property/add',
	EditProperty: ApiRootUrl + 'property/update',
	DeleteProperty: ApiRootUrl + 'property/delete',

	//======================订单相关接口=======================
	//条件查询
	GetOrderList: ApiRootUrl + 'order/list/',
	//预定接口，order
	AddOrder: ApiRootUrl + 'order/add',
	//办理入住
	StayIn: ApiRootUrl + 'order/stayIn', //修改订单接口，order
	//办理退房
	StayOut: ApiRootUrl + 'order/stayOut', //修改订单接口，order
	//办理续住
	ContinueIn: ApiRootUrl + 'order/continue', //修改订单接口，order
	//取消预订
	CancelPre: ApiRootUrl + 'order/cancelPre', //修改订单接口，order
	//删除订单接口
	DeleteOrder: ApiRootUrl + 'order/delete', 

	UpdateOrder: ApiRootUrl + 'order/updateOrder', //修改订单接口，order
	
	CancelOrder: ApiRootUrl + 'order/cancelOrder', //取消订单，order

	//======================入住人信息相关接口=======================
	//入住人信息列表查询
	GetOrderItems: ApiRootUrl + 'orderitem/list',

	GetCollect: ApiRootUrl + 'collect/getCollect/', //得到收藏接口，+this.$store.state.userInfo.username
	AddCollect: ApiRootUrl + 'collect/addCollect/', //添加收藏接口，collect
	DeleteCollect: ApiRootUrl + 'collect/deleteCollect/', //取消收藏接口，+this.collectionList[j].id

	//======================支付=======================
	Payment: ApiRootUrl + 'payment/pay',
	
	
	
	GetCommentTotal: ApiRootUrl +
		'comment/getCommentTotal/', //得到房屋评论数量接口，+this.houseData.username+'/'+this.houseData.title
	GetCommentList: ApiRootUrl +
		'comment/getCommentList/', //得到房屋评论接口，this.houseData.username+'/'+this.houseData.title+'/'+this.currentPage+'/'+this.pageSize
	AddComment: ApiRootUrl + 'comment/addComment', //添加评论接口，comment

	GetChatList: ApiRootUrl +
		'chat/getChatList/', //得到单个聊天记录接口，+this.$store.state.userInfo.username+'/'+this.thisusername
	GetUserChatList: ApiRootUrl + 'chat/getUserChatList/', //得到用户当前所有的聊天列表接口,即其他用户给他发的最后一条消息
	AddChat: ApiRootUrl + 'chat/addChat', //添加聊天记录



	GetPayment: ApiRootUrl + '/payment/getPaymentByUser/',
	/*得到付款记录接口*/
	DeletePayment: ApiRootUrl + '/payment/deletePaymentById/',
	/*删除付款记录接口*/

	WebSocketPath: 'ws://127.0.0.1:8081/websocket/', //WebSocket连接地址，+this.$store.state.userInfo.username

	Pay: ApiRootUrl + '/pay', //支付宝支付接口


};
export default API
