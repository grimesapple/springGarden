<!--房屋列表-->
<template>
	<article style="height: 90%">
		<div style="height: 50px;">
			<el-page-header  content="房源管理">
			</el-page-header>
		</div>
		<div class="loading" v-loading="loading" v-show="loading"></div>
		<!--搜索-->
		<div class="search" v-if="operation=='seeAll'">
			<span>房间名称：</span>
			<el-input placeholder="请输入内容" v-model="title" style="width: 250px" clearable>
			</el-input>
			<span>房间类型：</span>
			<el-input placeholder="请输入内容" v-model="title" style="width: 250px" clearable>
			</el-input>

			<div style="margin-left: 20px;height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-search" size="mini" @click="getHouseData()">搜索</el-button>
			</div>
			<span>共{{total}}条搜索结果</span>
		</div>
		<!-- 新增 -->
		<div class="search" v-if="operation=='seeAll'">
			<div style="height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-plus" size="mini"
					@click="dialogVisible = true,dialogType='add'">添加</el-button>
			</div>

		</div>

		<!--内容区域-->
		<el-table :data="tableData" height="90%" border stripe>
			<el-table-column prop="id" label="房屋id" width="70" align="center"></el-table-column>
			<el-table-column prop="name" label="房屋名字" align="center"></el-table-column>
			<el-table-column prop="subTitle" label="房屋标题" align="center"></el-table-column>
			<el-table-column prop="stock" label="总数量" align="center"></el-table-column>
			<el-table-column prop="orignalPrice" label="原价格(晚/元)" align="center"></el-table-column>
			<el-table-column prop="promotePrice" label="促销价格(晚/元)" align="center"></el-table-column>


			<el-table-column label="房屋照片" align="center">
				<template slot-scope="scope">
					<el-carousel trigger="click" :autoplay="false" height="96px" indicator-position="none">
						<el-carousel-item v-for="(imgItem,key) in scope.row.imgs" :key="key">
							<img ref="imgHeight" :src=imgItem width="100%" height="100%" object-fit="cover"
								v-show="imgItem!=null">
						</el-carousel-item>
					</el-carousel>
				</template>
			</el-table-column>

			<el-table-column label="操作" align="center">
				<template slot-scope="scope">
					<el-button @click="dialogType='edit', set(form, scope.row), dialogVisible=true" type="text"
						size="small">修改</el-button>

					<el-button @click="del(scope.row)" type="text" size="small">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<el-pagination background layout="prev, pager, next" :page-size=pageSize :total="total"
			:current-page="currentPage" @current-change="page" @next-click="currentPage += 1, page(currentPage)">
		</el-pagination>
		<el-dialog :visible.sync="dialogVisible" width="28%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="房间名称">
					<el-input v-model="form.name" placeholder="房间名称"></el-input>
				</el-form-item>
				<el-form-item label="房间标题">
					<el-input v-model="form.subTitle" placeholder="房间标题"></el-input>
				</el-form-item>
				<el-form-item label="房间价格">
					<el-input v-model="form.orignalPrice" placeholder="房间价格,单位:晚/元"></el-input>
				</el-form-item>
				<el-form-item label="促销价格">
					<el-input v-model="form.promotePrice" placeholder="促销价格,单位:晚/元"></el-input>
				</el-form-item>
				<el-form-item label="房间数量">
					<el-input v-model="form.stock" placeholder="数量,单位:间"></el-input>
				</el-form-item>
				<el-form-item label="房间类型">
					<el-select v-model="form.categoryId" placeholder="请选择房间类型" style="width: 100%;">
						<el-option v-for="item in categorys" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
				</el-form-item>

			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="addHouse(),dialogVisible = false" v-if="dialogType=='add'">确
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
				username: '',
				/*用户名*/
				title: '',
				/*房屋标题*/

				//添加弹出框
				dialogVisible: false,
				dialogType: "",
				form: {
					name: '大床房',
					subTitle: '浪漫',
					orignalPrice: '100',
					promotePrice: '80',
					stock: '1',
					categoryId: '1'
				},
				//房间类型
				categorys: [],

			}
		},
		created() {
			this.getHouseData()
		},
		methods: {
			async edit() { //修改接口
				const _this = this
				await this.updateHouse(_this.form)
				await this.getHouseData()
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
			async del(row) { //审核拒绝
				await this.delHouse(row)
				await this.getHouseData()
			},

			// open() {
			// 	this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
			// 		confirmButtonText: '确定',
			// 		cancelButtonText: '取消',
			// 		type: 'warning'
			// 	}).then(() => {
			// 		this.$message({
			// 			type: 'success',
			// 			message: '删除成功!'
			// 		});
			// 	}).catch(() => {
			// 		this.$message({
			// 			type: 'info',
			// 			message: '已取消删除'
			// 		});
			// 	});
			// },

			async updateHouse(item) { //修改房屋
				const _this = this
				let data = this.requestSend(this.API.UpdateHouse, item, "post");
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
			},
			async getHouseData(Page) { //得到房屋数据
				if (Page != null && Page != 'undefined') {
					this.currentPage = Page
				} else {
					this.currentPage = 1
				}
				this.loading = true
				const _this = this
				if (this.operation == "seeAll") {
					await axios.get(this.API.GetHouseByAdmin, {
						params: {
							pageNum: _this.currentPage,
							pageSize: _this.pageSize
						}
					}).then(resp => {
						console.log(resp.data)
						_this.total = resp.data.result.total
						_this.tableData = resp.data.result.list
					})
					
					// let data = _this.requestSend(this.API.GetCategory,null,"get");
					// console.log(data);
				}
				this.loading = false
			},
			page(currentPage) { //换页
				this.getHouseData(currentPage)
			},
			async addHouse() {
				const _this = this;
				let data = _this.requestSend(_this.API.AddHouse, _this.form, "post")
				console.log(data)
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
				this.getHouseData()
			},

			//发送请求
			requestSend(url, item, methods) {
				let data = {};
				let result = {};
				if (methods == "get" || methods == "GET") {
					axios.get(url, {
						params: item
					}).then(resp => {
						result = resp.data
					})
				} else {
					// let data = JSON.parse(JSON.stringify(item));
					data = QS.stringify(item)

					axios.post(url, data).then(resp => {
						result = resp.data
					})
				}
				return result;
			},

			//删除
			delHouse(row) {
				console.log(row.id);
				let data = {}
				data.id = row.id;
				this.requestSend(this.API.delHouse, data, "post");
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
</style>
