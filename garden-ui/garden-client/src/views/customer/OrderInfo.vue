<template>
	<article>
		<el-menu class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff"
			active-text-color="#ffd04b" style="display: flex;justify-content: flex-end">
			<el-menu-item index="1"><a href="/">主页</a></el-menu-item>
			<el-menu-item index="2"><a @click="loginOut">退出</a></el-menu-item>
			<el-menu-item index="3"><a href="/MerchantManage" target="_blank">商户系统</a></el-menu-item>
		</el-menu>
		<div class="orderT-fill-page">
			<div class="orderT-main">
				<div class="orderinfo-cont">
					<div class="col-header">
						<span class="col-title">预订信息</span>
					</div>
					<div class="orderinfo-main">
						<el-form label-width="80px">
							<el-form-item label="入住时段" style="margin-bottom: 0">
								<span>{{currentHouseData.chooseDate[0]}}至{{currentHouseData.chooseDate[1]}}</span>
							</el-form-item>
							<el-form-item label="支付金额" style="margin-bottom: 0">
								<span>{{totalPrice}}</span>
							</el-form-item>
							<el-form-item label="民宿名称" style="margin-bottom: 0">
								<span>{{currentHouseData.house.subTitle}}</span>
							</el-form-item>
							<!--                            <el-form-item label="商家电话" style="margin-bottom: 0">
                                <span>{{currentHouseData.house.username}}</span>
                            </el-form-item> -->
						</el-form>
					</div>
					<div class="col-header">
						<span class="col-title">入住人信息</span>
					</div>
					<div class="orderinfo-main">
						<el-form label-width="120">
							<el-form-item label="入住人数：" style="margin-bottom: 0">
								<el-select v-model="form.number" placeholder="请输入人数" :disabled="!isEdit">
									<el-option v-for="(item,index) in options" :label="item.value+'人'"
										:value="item.value"></el-option>
								</el-select>
							</el-form-item>
							<el-form-item label="姓名：">
								<el-input v-model="form.orderitems[0].receiver" :disabled="!isEdit"></el-input>
							</el-form-item>
							<el-form-item label="电话号码：">
								<el-input v-model="form.orderitems[0].mobile" :disabled="!isEdit"></el-input>
							</el-form-item>
							<el-form-item label="身份证号：">
								<el-input v-model="form.orderitems[0].cardid" :disabled="!isEdit"></el-input>
							</el-form-item>
						</el-form>
					</div>
					<form action="http://localhost:8081/pay" method="post" v-show="false">
						订单名称：<input type="text" name="WIDsubject" v-model="totalPrice" required><br />
						付款金额：<input type="text" name="WIDtotal_amount" v-model="totalPrice" required><br />
						WIDbody：<input type="text" name="WIDbody" v-model="currentHouseData.title"><br />
						<input type="submit" value="下单">
					</form>
					<el-button :type="isEdit?'info':'warning'" @click="isEdit=!isEdit" style="margin-right:50%">
						{{isEdit?'关闭编辑':'编辑信息'}}
					</el-button>
					<el-button type="success" style="margin: 0px" @click="placeOrder">提交订单</el-button>
				</div>
			</div>
		</div>
	</article>
</template>

<script>
	export default {
		props: ["houseData", "totalPrice"],
		data() {
			return {
				currentHouseData: [],
				/*当前房屋信息*/
				form: {
					realname: '',
					startTime: '',
					endTime: '',
					number: '',
					userId: '',
					productId: '',
					orderitems: [{
						receiver: '',
						cardid: '',
						mobile: '',
					}, ]
				},
				isEdit: false,
				options: [],
				//判断是否为完善的信息
				flag: true,
			}
		},
		created() {
			this.currentHouseData = JSON.parse(this.houseData)
			this.form.startTime = this.currentHouseData.chooseDate[0]
			this.form.endTime = this.currentHouseData.chooseDate[1]
			this.form.productId = this.currentHouseData.house.id
			this.form.number = this.currentHouseData.people
			this.form.userId = this.$store.state.userInfo.userId

			for (let i = 0; i < this.currentHouseData.house.people; i++) {
				this.options[i] = {
					value: i + 1,
				}
			}

			this.getOrderItem()

		},
		methods: {
			loginOut() { //退出登录
				this.$store.state.isLogin = false
				this.$store.state.userInfo = []
				sessionStorage.setItem('store', null)
				this.$router.push({
					path: "/",
				})
			},
			placeOrder() {
				/*提交订单*/
				// let orderT = {
				// 	"id": null,
				// 	"timeSlot": this.currentHouseData.chooseDate.toString(),
				// 	"money": this.totalPrice,
				// 	"title": this.currentHouseData.title,
				// 	"username": this.currentHouseData.username,
				// 	"customerId": this.$store.state.userInfo.username,
				// 	"state": 1
				// }

				const _this = this
				_this.form.realname = _this.form.orderitems[0].receiver
				let key
				for (key in this.form) {
					console.log("判断：" + key)
					if (key == "orderitems") {
						for (let v in this.form[key]) {
							console.log("判断：" + v + "  " + this.form[key]. [v])
							if (this.form[key]. [v] == '' || this.form[key]. [v] == null) {
								console.log("发送信息" + key)
								_this.$message({
									message: '请先完善个人信息：' + key,
									type: 'warning'
								})
								return false
							}
						}
					}
					if (this.form[key] === "") {
						console.log("发送信息" + key)
						_this.$message({
							message: '请先完善个人信息：' + key,
							type: 'warning'
						})
						return false
					}
					console.log("未发送信息" + key)
				}

				if (!this.flag) {
					//将信息保存到用户信息中
					let userInfo = {
						id: this.$store.state.userInfo.userId,
						realname: _this.form.orderitems[0].receiver,
						telphone: _this.form.orderitems[0].mobile,
						cardid: _this.form.orderitems[0].cardid,
					}
					axios.post(this.API.UpdateUser, userInfo).then(function(resp) {
						if (resp.data.code == 500) {
							_this.$notify.error({
								title: '错误',
								message: '保存个人信息错误'
							});
						}
						if (resp.data.code == 200) {
							_this.$notify({
								title: '成功',
								message: '保存个人信息成功',
								type: 'success'
							});
						}
					})
				}

				axios.post(this.API.AddOrder, this.form).then(function(resp) {
					if (resp.data.code == 500) {
						_this.$notify.error({
							title: '错误',
							message: '该房间已经被他人抢先预定!'
						});
					}
					if (resp.data.code == 200) {
						_this.$notify({
							title: '成功',
							message: '您已成功预定!',
							type: 'success'
						});
						setTimeout(() => {
							_this.$router.push({
								path: "/Order/user"
							})
						}, 3000);
					}
				})
			},
			// Js
			changeSelect() {
				this.$forceUpdate() // 强制刷新
			},
			getOrderItem() {
				const _this = this

				axios.get(this.API.GetUser, {
					params: {
						id: this.$store.state.userInfo.userId,
					}
				}).then(function(resp) {

					let data = resp.data.result
					if (resp.data.code == 200) {
						console.log("resp")
						console.log(resp)
						_this.form.realname = data.realname
						_this.form.orderitems[0].receiver = data.realname
						_this.form.orderitems[0].cardid = data.cardid
						_this.form.orderitems[0].mobile = data.telphone
						if (_this.form.realname == null) {
							_this.flag = false
						}
						_this.changeSelect()
					}
					if (resp.data.code == 500) {


					}
				})
			}
		}
	}
</script>

<style scoped>
	.orderT-fill-page {
		margin: 0 auto;
		width: 1190px;
	}

	.orderT-main {
		margin: 15px 0;
		padding: 40px 30px;
		width: 800px;
		box-shadow: 0 0 3px 0 #ccc;
		box-sizing: inherit;
	}

	/*预定信息*/
	.orderinfo-cont {
		margin-bottom: 36px;
	}

	.col-header {
		width: 800px;
		padding: 8px 0;
		border-bottom: 2px solid #ddd;
		display: flex;
		justify-content: flex-start;
	}

	.col-title {
		padding-left: 8px;
		border-left: 3px solid #fd8238;
		font-size: 18px;
		font-weight: 700;
		color: #333;
	}

	.orderinfo-main {
		margin-top: 8px;
		font-size: 14px;
		color: #333;
	}

	/*    .submit-orderT-btn{
        margin: 18px auto 0;
        width: 124px;
        height: 36px;
        line-height: 36px;
        font-size: 16px;
        border-radius: 2px;
        cursor: pointer;
        margin-top: 56px;
    } */
</style>
