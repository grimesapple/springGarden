<!--房屋列表-->
<template>
	<article style="height: 90%">
		<div style="height: 50px;">
			<el-page-header content="订单管理">
			</el-page-header>
		</div>
		<div class="loading" v-loading="loading" v-show="loading"></div>
		<!--搜索-->
		<div class="search">
			<el-input class="inputS" placeholder="手机尾号" v-model="phone" style="width: 180px" clearable>
			</el-input>
			<el-input class="inputS" placeholder="账号名" v-model="username" style="width: 180px" clearable>
			</el-input>
			<el-select class="inputS" placeholder="订单状态" v-model="status" style="width: 180px" clearable>
				<el-option label="离店" value="0"></el-option>
				<el-option label="已预订" value="1"></el-option>
				<el-option label="已入住" value="2"></el-option>
				<el-option label="取消订单" value="3"></el-option>
			</el-select>
			<el-input class="inputS" placeholder="订单编号" v-model="orderCode" style="width: 180px" clearable>
			</el-input>
			<!-- 	<el-input placeholder="请输入内容" v-model="startTime" style="width: 180px" clearable>
			</el-input> -->
			<div style="margin-left: 20px;height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-search" size="mini" @click="getData()">搜索</el-button>
			</div>
			<span>共{{total}}条搜索结果</span>
		</div>
		<!-- 新增 -->
		<!-- 		<div class="search">
			<div style="height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-plus" size="mini"
					@click="dialogVisible = true,dialogType='add'">添加</el-button>
			</div>
		</div> -->

		<!--内容区域-->
		<el-table :data="tableData" height="90%" stripe
			:header-cell-style="{'color': 'rgb(81, 90, 110)'}">
			<el-table-column type="index" label="序号" width="70" align="center"></el-table-column>
			<el-table-column prop="orderCode" label="订单编号" width="190" align="center"></el-table-column>
			<el-table-column prop="productName" label="房间号" align="center"></el-table-column>
			<el-table-column prop="realName" label="姓名" align="center"></el-table-column>
			<el-table-column prop="phone" label="手机号码" align="center"></el-table-column>
			<el-table-column label="入住-离店" width="190" align="center">
				<template slot-scope="scope">
					<span> {{ scope.row.startTime }} 至 {{ scope.row.endTime }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="status" label="状态" align="center">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.status==0" type="info">离店</el-tag>
					<el-tag v-else-if="scope.row.status==1">已预定</el-tag>
					<el-tag v-else-if="scope.row.status==2" type="success">已入住</el-tag>
					<el-tag v-else-if="scope.row.status==3" type="danger">取消订单</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="totalCost" label="总价格" align="center">
				<template slot-scope="scope">
					<span><b> {{ scope.row.totalCost }} 元</b></span>
				</template>
			</el-table-column>
			<el-table-column prop="remark" label="备注" align="center"></el-table-column>
			<el-table-column label="操作" width="200px" align="center">
				<template slot-scope="scope">
					<el-button v-if="scope.row.status == 1" @click="stayIn(scope.row)" type="text"
						style="margin-right: 10px;">入住
					</el-button>
					<el-popconfirm v-if = "scope.row.status == 1" title="确定取消预订？" @confirm="cancelPre(scope.row)">
						<el-button type="text" slot="reference" >取消预订</el-button>
					</el-popconfirm>
					<el-popconfirm v-if = "scope.row.status == 2" title="确定退房吗？" @confirm="stayOut(scope.row)">
						<el-button type="text" slot="reference" >退房</el-button>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>

		<el-pagination background layout="prev, pager, next" :page-size=pageSize :total="total"
			:current-page="currentPage" @current-change="page" @next-click="currentPage += 1, page(currentPage)">
		</el-pagination>
		<!-- 新增窗口 -->
		<el-dialog :visible.sync="dialogVisible" width="28%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="房间类型">
					<el-input v-model="form.categoryId" placeholder="房间类型"></el-input>
				</el-form-item>
				<el-form-item label="属性名称">
					<el-input v-model="form.name" placeholder="属性名称"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="add(),dialogVisible = false" v-if="dialogType=='add'">确
					定</el-button>
				<el-button type="primary" @click="edit(), dialogVisible = false" v-if="dialogType=='edit'">确 定
				</el-button>
			</span>
		</el-dialog>
	</article>
</template>

<script>
	import QS from 'qs';
	export default {
		props: ["operation"],
		data() {
			return {
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
				state: '',
				/*状态*/
				city: '',
				/*城市*/
				/*手机尾号*/
				phone: '',
				/*账号名*/
				username: '',
				/*状态*/
				status: '',
				/*订单编号*/
				orderCode: '',
				/*入住时间*/
				phone: '',
				/*手机尾号*/
				startTime: '',
				/*属性名称*/
				name: '',
				//添加弹出框
				dialogVisible: false,
				dialogType: "",
				form: {
					//房间类型
					categoryId: '',
					//属性名称
					name: '',
				},
				//房间类型
				categorys: [],
				//图片上传地址
				actionurl: "",

			}
		},
		created() {
			this.getData()
		},
		methods: {
			async edit() { //修改接口
				const _this = this
				await this.update(_this.form)
				await this.getData()
			},
			async add() { //添加接口
				const _this = this
				await this.addData()
				await this.getData()
			},
			async del(row) { //删除接口
				await this.delData(row)
				await this.getData()
			},

			set(form, row) {
				form.id = row.id
				form.name = row.name
				form.subTitle = row.subTitle
				form.orignalPrice = row.orignalPrice
				form.promotePrice = row.promotePrice
				form.stock = row.stock
				form.categoryId = row.categoryId
			},
			async addData() {
				//添加
				const _this = this;
				await axios.post(_this.API.AddProperty, _this.form).then(res => {
						let data = res.data.result;
						if (data.code == 200) {
							_this.$message({
								message: '操作完成',
								type: 'success'
							})
						}
						if (data.code == 500) {
							_this.$message({
								showClose: true,
								message: '系统错误',
								type: 'error'
							})
						}
					})
					.catch(err => {
						console.log(err.message)
					})

				this.getData()
			},
			async update(item) { //修改房屋
				const _this = this
				await axios.post(_this.API.EditProperty, item).then(res => {
						let data = res.data.result;
						if (data.code == 200) {
							_this.$message({
								message: '操作完成',
								type: 'success'
							})
						}
						if (data.code == 500) {
							_this.$message({
								showClose: true,
								message: '系统错误',
								type: 'error'
							})
						}
					})
					.catch(err => {
						console.log(err.message)
					})
			},
			//删除
			async delData(row) {
				console.log(row.id);
				let data = {}
				data.id = row.id;
				await axios.get(this.API.DeleteProperty, {
						params: {
							id: row.id
						}
					}).then(res => {
						let data = res.data.result;
						if (data.code == 200) {
							_this.$message({
								message: '操作完成',
								type: 'success'
							})
						}
						if (data.code == 500) {
							_this.$message({
								showClose: true,
								message: '系统错误',
								type: 'error'
							})
						}
					})
					.catch(err => {
						console.log(err.message)
					})
			},
			//数据查询
			async getData(Page) {
				if (Page != null && Page != 'undefined') {
					this.currentPage = Page
				} else {
					this.currentPage = 1
				}
				this.loading = true
				const _this = this

				let data = {
					phone: this.phone,
					username: this.username,
					status: this.status,
					orderCode: this.orderCode,
					pageNum: _this.currentPage,
					pageSize: _this.pageSize
				};
				if (_this.name != '') {
					data. ["name"] = _this.name
				}
				await axios.get(this.API.GetOrderList, {
					params: data
				}).then(resp => {
					console.log(resp.data)
					_this.total = resp.data.result.total
					_this.tableData = resp.data.result.list
				})

				this.loading = false
			},
			page(currentPage) { //换页
				this.getData(currentPage)
			},
			//退房
			async stayOut(row){
				console.log(row)
				let _this = this
				let item = {
					id : row.orderId
				}
				await axios.post(_this.API.StayOut, item).then(res => {
						let data = res.data.result;
						if (data.code == 200) {
							_this.$message({
								message: res.data.msg,
								type: 'success'
							})
						}
						if (data.code == 500) {
							_this.$message({
								showClose: true,
								message: res.data.msg,
								type: 'error'
							})
						}
					})
					.catch(err => {
						console.log(err.message)
					})
				await this.getData()
			},
			//取消预订
			async cancelPre(row){
				console.log(row)
				let _this = this
				let item = {
					id : row.orderId
				}
				await axios.post(_this.API.CancelPre, item).then(res => {
						let data = res.data.result;
						if (data.code == 200) {
							_this.$message({
								message: res.data.msg,
								type: 'success'
							})
						}
						if (data.code == 500) {
							_this.$message({
								showClose: true,
								message: res.data.msg,
								type: 'error'
							})
						}
					})
					.catch(err => {
						console.log(err.message)
					})
				await this.getData()
			},

			//发送请求
			async requestSend(url, item, methods) {
				let data = {};
				let result = {};
				if (methods == "get" || methods == "GET") {
					await axios.get(url, {
						params: item
					}).then(resp => {
						result = resp.data
					})
				} else {
					// let data = JSON.parse(JSON.stringify(item));
					data = QS.stringify(item)

					await axios.post(url, data).then(resp => {
						result = resp.data
					})
				}
				return result;
			},

			//跳转到入住页面
			stayIn(row) {
				this.$router.push({
					path: "/OrderListInfo/stay",
					query: {
						data: JSON.stringify(row)
					}
				})
			}
		},
	}
</script>

<style scoped>
	/*滚动条样式*/
	*::-webkit-scrollbar {
		/*滚动条整体样式*/
		width: 5px;
		/*高宽分别对应横竖滚动条的尺寸*/
		height: 0px;
	}

	*::-webkit-scrollbar-thumb {
		/*滚动条里面小方块*/
		border-radius: 10px;
		background-color: #a2a3a5;
	}

	*::-webkit-scrollbar-track {
		/*滚动条里面轨道*/
		box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
		background: #ededed;
		border-radius: 10px;
	}

	/*加载图标*/
	.loading {
		position: fixed;
		width: 100vw;
		height: 100vh;
		z-index: 100;
		top: 0%;
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
