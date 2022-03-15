<!--订单信息，添加/修改-->
<template>
	<section class="g-createHouse-layout">
		<!--步骤条-->
		<div class="g-comStepNav-layout">
			<el-steps :active="active" finish-status="success" class="step-nav">
				<el-step title="添加入住信息"></el-step>
				<el-step title="选定房间"></el-step>
				<!--出租类型，床数，人数，户型，标题-->
				<el-step title="添加住户信息"></el-step>
			</el-steps>
		</div>
		<!--内容-->
		<section class="g-hcComModal-layout" v-show="active===0">
			<div class="situation-item">
				<div class="item-title">
					<h2>入住时间</h2>
				</div>
				<div class="item-contain">
					<el-date-picker format="yyyy-MM-dd" value-format="yyyy-MM-dd" v-model="chooseDate" type="daterange"
						range-separator="-" start-placeholder="入住日期" end-placeholder="离店日期"
						:picker-options="pickerOptions">
					</el-date-picker>
				</div>
				<div class="item-title">
					<h2>入住人数</h2>
				</div>
				<div class="item-contain">
					<el-select v-model="peopleNumber" placeholder="请选择人数" size="small" @change="changePeople">
						<el-option v-for="item in Options" :key="item.value" :label="item.value+'人'"
							:value="item.value">
						</el-option>
					</el-select>
				</div>
				<div class="item-title">
					<h2>床数</h2>
				</div>
				<div class="item-contain">
					<el-select v-model="bedNumber" placeholder="请选择床数" size="small">
						<el-option v-for="item in Options" :key="item.value" :label="item.value+'床'"
							:value="item.value">
						</el-option>
					</el-select>
				</div>
			</div>
		</section>
		<section class="g-hcComModal-layout" v-show="active===1">
			<div class="situation-item">
				<div class="search">
					<el-input class="inputS" placeholder="房间号码" v-model="name" type="text" style="width: 150px"
						clearable>
					</el-input>
					<el-input class="inputS" placeholder="房间标题" v-model="subTitle" type="text" style="width: 150px"
						clearable>
					</el-input>
					<el-button type="primary" style=" height: 30px;margin-left: 20px;width: 65px; margin-top: 5px;"
						icon="el-icon-search" size="mini" @click="getHouseData()">搜索
					</el-button>
					<span>共{{total}}条搜索结果</span>
					<el-pagination style="margin: 5px 0 0 50px;" background layout="prev, pager, next"
						:page-size=pageSize :total="total" :current-page="currentPage" @current-change="page"
						@next-click="currentPage += 1, page(currentPage)">
					</el-pagination>
				</div>
				<div class="search" v-if="this.chooseHouse !=''">
					<span>已选中：{</span>
					<span>房间号码：{{this.chooseHouse.name}}</span>
					<span>房间标题：{{this.chooseHouse.subTitle}}</span>
					<span>价格：{{this.chooseHouse.promotePrice}}</span>
					<span>}</span>
				</div>
				<!--内容区域-->
				<el-table :data="tableData" height="90%" border stripe highlight-current-row @current-change="choese">
					<el-table-column type="index" align="center" label="序号"></el-table-column>
					<el-table-column prop="house.name" label="房屋名字" align="center"></el-table-column>
					<el-table-column prop="house.subTitle" label="房屋标题" align="center"></el-table-column>
					<el-table-column label="房屋照片" width="210" align="center">
						<template slot-scope="scope">
							<el-carousel trigger="click" :autoplay="false" height="96px" indicator-position="none">
								<el-carousel-item v-for="(imgItem,key) in scope.row.img" :key="key">
									<img ref="imgHeight" :src="$data.root+imgItem.url" width="100%" height="100%"
										object-fit="cover" v-show="imgItem!=null">
								</el-carousel-item>
							</el-carousel>
						</template>
					</el-table-column>
					<el-table-column prop="house.bedNumber" label="床数量" align="center"></el-table-column>
					<el-table-column prop="house.people" label="最大入住人数" align="center"></el-table-column>
					<el-table-column prop="house.orignalPrice" label="原价格(元/晚)" align="center"></el-table-column>
					<el-table-column prop="house.promotePrice" label="促销价格(元/晚)" align="center"></el-table-column>
					<el-table-column prop="tag" label="其他属性" align="center">
						<template slot-scope="scope">
							<el-tag style="margin: 0 5px 0 5px;" size="mini" v-for="(imgItem,key) in scope.row.property"
								:key="key" :type="key%2 === 1 ? 'primary' : 'success'" disable-transitions>
								{{imgItem.propertyName}}
							</el-tag>
						</template>
					</el-table-column>
				</el-table>
			</div>
		</section>
		<section class="g-hcComModal-layout" v-show="active===2">
			<div class="situation-item1" v-for="(item,key) in orderitems" :key="key">
				<div class="item-title" style="margin-top: 50px">
					<h2>住户姓名 {{key+1}}</h2>
				</div>
				<div class="item-contain">
					<el-input v-model="item.receiver" placeholder="请输入住户姓名" style="width: 150px" size="small"
						maxlength="12" clearable></el-input>
				</div>
				<div class="item-title">
					<h2>联系方式</h2>
				</div>
				<div class="item-contain">
					<el-input v-model="item.mobile" placeholder="请输入联系方式" style="width: 150px" size="small"
						maxlength="11" clearable></el-input>
				</div>
				<div class="item-title">
					<h2>身份证号码</h2>
				</div>
				<div class="item-contain">
					<el-input v-model="item.cardid" placeholder="请输入身份证号码" style="width: 150px" size="small"
						maxlength="18" clearable></el-input>
				</div>
			</div>
		</section>
		<div class="footer">
			<el-button style="margin-top: 12px;" @click="prev" v-show="active!=0">上一步</el-button>
			<el-button style="margin-top: 12px;" @click="next" v-show="active!=2">下一步</el-button>
			<el-button style="margin-top: 12px;" v-if="this.operation == 'add'" @click="addData('pre')"
				v-show="active===2">
				办理预定</el-button>
			<el-button style="margin-top: 12px;" @click="addData('stayin')" v-show="active===2">办理入住</el-button>
		</div>
	</section>
</template>

<script>
	import {
		client
	} from '../utils/alioss';
	import {
		quillEditor
	} from 'vue-quill-editor';
	import 'quill/dist/quill.core.css'
	import 'quill/dist/quill.snow.css'
	import 'quill/dist/quill.bubble.css'
	import QS from 'qs';
	export default {
		components: {
			quillEditor
		},
		props: ["operation", "orderData"],
		data() {
			return {
				/*活动的步骤*/
				active: 0,
				/*房间id*/
				id: '',
				/*床数*/
				bedNumber: '',
				/*入住人数*/
				peopleNumber: '',
				/*房间号*/
				name: '',
				/*标题*/
				subTitle: '',
				/*市场价格*/
				housePrice: '',
				/*促销价格*/
				promotePrice: '',
				/*入住时段*/
				chooseDate: [],
				//房间数据
				houseData: '',
				//选中房间
				chooseHouse: "",
				/*住户姓名*/
				realname: '',
				/*联系方式*/
				phone: '',
				/*身份证号码*/
				idcard: '',
				tableData: [],
				/*表格数据*/
				pageSize: 5,
				/*每页显示数量*/
				total: 0,
				/*房屋总数*/
				currentPage: 1,
				/*当前页码*/
				loading: false,
				/*整个页面是否显示加载图标*/
				title: '',
				/*房屋标题*/
				root: this.API.ShowImage,
				//添加弹出框
				dialogVisible: false,
				dialogType: "",
				form: {
					id: '',
					people: '',
					startTime: '',
					bedNumber: '',
					endTime: '',
				},
				allField: {
					startTime: '',
					endTime: '',
					number: '',
					productId: '',
					status: '',
					orderitems: []
				},
				//入住人数下拉框
				Options: [{
					value: '1'
				}, {
					value: '2'
				}, {
					value: '3'
				}, {
					value: '4'
				}],
				//限定不能选择今日之前的时间
				pickerOptions: {
					/*限制只能选择今天及以后的时间*/
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				currentPage: 1,
				orderitems: [{
						receiver: '',
						mobile: '',
						cardid: ''
					},
					{
						receiver: '',
						mobile: '',
						cardid: ''
					},
					{
						receiver: '',
						mobile: '',
						cardid: ''
					},
					{
						receiver: '',
						mobile: '',
						cardid: ''
					},
				],
			};
		},
		mounted() {},
		async created() {
			if (this.operation == "add") {
				console.log("添加")
			}
			if (this.operation == "stay") {
				this.orderData = JSON.parse(this.orderData)
				console.log("data")
				console.log(this.orderData)
				//查询房间信息
				this.form.id = this.orderData.productId
				this.chooseDate[0] = this.orderData.startTime
				this.chooseDate[1] = this.orderData.endTime
				this.peopleNumber = this.orderData.number
				await this.getHouseData();
				this.bedNumber = this.tableData[0].house.bedNumber
				this.choese(this.tableData[0])
				await this.getPeopleList(this.orderData.orderId)
				// this.active == 2

			}
			// this.getHouseData()
		},
		methods: {
			//上一步
			prev() {
				--this.active
				if (this.active < 0) this.active = 0;
			},
			//下一步
			async next() {
				if (this.active == 0) {
					if (this.peopleNumber != '' && this.bedNumber != '' && this.chooseDate != '') {
						this.form.people = this.peopleNumber
						this.form.bedNumber = this.bedNumber
						this.form.startTime = this.chooseDate[0]
						this.form.endTime = this.chooseDate[1]
						await this.getHouseData(1);
						console.log("查询")
							++this.active
					} else {
						this.$message({
							type: 'warning',
							message: '请完善信息!'
						});
					}
				} else if (this.active == 1) {
					if (this.chooseHouse == '') {
						this.$message({
							type: 'warning',
							message: '请选择房间!'
						});
					} else {
						++this.active
					}
				}
				// ++this.active
				if (this.active > 2) this.active = 2;
			},
			//根据条件查询房间信息
			async getHouseData(Page) {
				//查询条件
				let _this = this
				if (Page != null && Page != 'undefined') {
					this.currentPage = Page
				} else {
					this.currentPage = 1
				}
				await axios.get(this.API.GetHouseList, {
					params: this.form
				}).then(resp => {
					console.log(resp.data)
					_this.total = resp.data.result.total
					let data = resp.data.result.list
					let resutl = []
					let i = 0;
					if (this.form.id == '') {
						data.forEach(item => {
							if (item.status == 0) {
								resutl[i++] = item
							}
						})
						_this.tableData = resutl
					} else {
						_this.tableData = data
					}

					console.log(_this.tableData)
					// console.log(resutl)
				})
			},
			//选中房间信息
			choese(res) {
				this.chooseHouse = res.house
				console.log(res)
			},
			page(currentPage) { //换页
				this.getHouseData(currentPage)
			},
			//添加订单
			async addData(type) {
				this.allField.startTime = this.chooseDate[0]
				this.allField.endTime = this.chooseDate[1]
				this.allField.number = this.peopleNumber
				this.allField.productId = this.chooseHouse.id
				this.allField.orderitems = this.orderitems
				if (this.operation == 'add') {
					//新增
					let message = '';
					if (type == 'pre') {
						//只预定
						this.allField.status = 1
						message = "预定"
					} else {
						//入住
						this.allField.status = 2
						message = "入住"
					}
					await axios.post(this.API.AddOrder, this.allField).then(resp => {
						let _this = this
						if (resp.data.code == 200) {
							_this.$message({
								message: message + '成功',
								type: 'success'
							})
							setTimeout(() => {
								_this.$router.push({
									path: "/OrderList/seeAll"
								})
							}, 1000);
						}
						if (resp.data.code == 500) {
							_this.$message({
								showClose: true,
								message: message + '失败',
								type: 'error'
							})
						}
						console.log(resp.data)
						_this.total = resp.data.result.total
						_this.tableData = resp.data.result.list
					})
				} else {
					//更新
					await axios.post(this.API.StayIn, this.allField).then(resp => {
						let _this = this
						if (resp.data.code == 200) {
							_this.$message({
								message: '入住成功',
								type: 'success'
							})
							setTimeout(() => {
								_this.$router.push({
									path: "/OrderList/seeAll"
								})
							}, 1000);
						}
						if (resp.data.code == 500) {
							_this.$message({
								showClose: true,
								message: '入住失败',
								type: 'error'
							})
						}
					})

				}


			},
			//改变入住人信息数量
			changePeople() {
				let item = []
				for (let i = 0; i < this.peopleNumber; i++) {
					item[i] = {
						receiver: '',
						mobile: '',
						cardid: '',
					}
				}
				this.orderitems = item
			},
			//格式化表格数据
			format(value) {
				console.log("row")
				console.log(value)
			},
			//获取入住人信息
			async getPeopleList(orderId) {
				await axios.get(this.API.GetOrderItems, {
					params: {
						orderId: orderId
					}
				}).then(resp => {
					let _this = this
					if (resp.data.code == 200) {
						this.orderitems = resp.data.result.list
						if (this.peopleNumber > this.orderitems.length) {
							let item = []
							for (let i = this.peopleNumber+1; i < this.peopleNumber - this.peopleNumber; i++) {
								this.orderitems[i] = {
									receiver: '',
									mobile: '',
									cardid: '',
								}
							}
						}
					}
					if (resp.data.code == 500) {

					}
				})
			}
		}
	}
</script>

<style scoped>
	* {
		margin: 0;
		padding: 0;
	}

	.g-createHouse-layout {
		background: #fff;
		border: 1px solid rgba(0, 0, 0, 0);
		-webkit-box-shadow: 0 0 0 1px rgba(63, 63, 68, .05), 0 1px 3px 0 rgba(63, 63, 68, .15);
		box-shadow: 0 0 0 1px rgba(63, 63, 68, .05), 0 1px 3px 0 rgba(63, 63, 68, .15);
		-webkit-border-radius: 2px;
		border-radius: 2px;
		color: #333;
		text-align: left;
	}

	.g-comStepNav-layout {
		box-shadow: 0 4px 48px 20px rgba(0, 0, 0, .06);
	}

	.step-nav {
		padding: 5px 20px 0 20px;
	}

	/*主要步骤区域*/
	.g-hcComModal-layout {
		height: 600px;
		padding: 20px 40px 0 40px;
		display: flex;
		overflow: hidden;
	}

	.footer {
		padding-right: 20px;
		display: flex;
		justify-content: flex-end;
	}

	/*位置*/
	.g-hcComModal-layout .title {
		font-size: 24px;
		font-weight: 700;
	}

	.region-left {
		width: 30%;
	}

	.region-right {
		width: 70%;
	}

	.g-hcComModal-layout h5 {
		margin-top: 60px;
		font-weight: 700;
		font-size: 16px;
		color: #333;
	}

	.country-city-box {
		margin-top: 12px;
		display: -webkit-box;
		display: -webkit-flex;
		display: -ms-flexbox;
		display: flex;
	}

	/*概况*/
	.situation-item {
		width: 100%;
	}

	.situation-item1 {
		width: 20%;

	}


	.item-title,
	.item-contain {
		width: 100%;
		margin: 15px 10px 10px 10px;
		padding-left: 50px;
	}

	.item-checkList {
		width: 40%;
		padding: 5px;
	}

	.item-check {
		width: 80px;
	}

	/*照片*/
	.photo-wrapper .item-title {
		background: #f5f5f5;
		height: 60px;
		padding: 0px 40px;
		margin: 40px 0 0 0;
		width: 100%;
	}

	.item-title-left {
		float: left;
		height: 100%;
		display: flex;
		-webkit-box-align: center;
		align-items: center;
	}

	.item-title-left h2 {
		font-weight: 700;
		font-size: 16px;
		color: #333;
		line-height: 21px;
	}

	.item-title-left h2 span {
		font-size: 12px;
		font-weight: 400;
		line-height: 16px;
		margin-left: 9px;
	}

	.item-title-left p {
		font-size: 12px;
		font-weight: 400;
		color: #999;
		line-height: 16px;
		margin-top: 4px;
		text-align: left;
	}

	.upload-container {
		margin-top: 5px;
		margin-right: 20px;
	}

	.ivu-form-item {
		vertical-align: top;
	}

	.ivu-form-item-content {
		position: relative;
		line-height: 32px;
		font-size: 12px;
		display: flex;
		justify-content: flex-start;
		flex-wrap: wrap;
	}

	.photo-item {
		margin-right: 10px;
		margin-bottom: 10px;
		width: 263px;
	}

	.photo-List {
		height: 180px;
		border-radius: 2px;
		border: 1px solid #ddd;
		margin-left: 20px;
	}

	.photo-List .img-con {
		width: 100%;
		height: 100%;
		position: relative;
		cursor: pointer;
	}

	.photo-List .uploadImg {
		width: 100%;
		height: 100%;
		border-radius: 2px;
		object-fit: contain;
		border-style: none;
	}

	/*时段和价格*/
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
		width: 100%
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 256px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 260px;
		height: 178px;
		display: block;
	}

	.input-item-contain {
		width: 400px;
		height: 242px;
	}

	/*查询*/
	.search {
		height: 45px;
		display: flex;
	}

	.search>span {
		line-height: 35px;
		font-weight: 700;
		margin: 0 5px;
		height: 100%;
	}

	.inputS {
		margin-right: 10px;
	}
</style>
<style>
	.ql-editor {
		height: 120px;
	}
</style>
