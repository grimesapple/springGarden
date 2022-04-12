<!--房屋列表-->
<template>
	<article style="height: 90%">
		<div style="height: 50px;">
			<span style="font-size: 23px;color: #303133; display: flex; margin-left: -5px;">设施管理</span>
		</div>
		<div class="loading" v-loading="loading" v-show="loading"></div>
		<!--搜索-->
		<div class="search">
			<span>属性名称：</span>
			<el-input placeholder="请输入内容" v-model="name" style="width: 250px" clearable>
			</el-input>
<!-- 			<span>房间类型：</span>
			<el-input placeholder="请输入内容" v-model="name" style="width: 250px" clearable>
			</el-input> -->

			<div style="margin-left: 20px;height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-search" size="mini" @click="getData()">搜索</el-button>
			</div>
			<span>共{{total}}条搜索结果</span>
		</div>
		<!-- 新增 -->
		<div class="search">
			<div style="height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-plus" size="mini"
					@click="dialogVisible = true,dialogType='add'">添加</el-button>
			</div>

		</div>

		<!--内容区域-->
		<el-table :data="tableData" height="90%" border stripe>
			<el-table-column prop="id" label="序号" width="70" type="index" align="center"></el-table-column>
			<!-- <el-table-column prop="cName" label="房间类型" align="center"></el-table-column> -->
			<el-table-column prop="name" label="属性名称" align="center"></el-table-column>
			<el-table-column label="操作" align="center">
				<template slot-scope="scope">
					<el-button @click="dialogType='edit', set(form, scope.row), dialogVisible=true" type="text"
						size="small">修改</el-button>
					<el-popconfirm title="确定删除吗？" @confirm="del(scope.row)">
						<el-button   type="text"  size="small" slot="reference" >删除</el-button>
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
<!-- 				<el-form-item label="房间类型">
					<el-input v-model="form.categoryId" placeholder="房间类型"></el-input>
				</el-form-item> -->
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
				pageSize: 11,
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
			async del(row) { //审核拒绝
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

				// let data = _this.requestSend(_this.API.AddHouse, _this.form, "post")
				// console.log(data)
				// if (data.code == 200) {
				// 	_this.$message({
				// 		message: '操作完成',
				// 		type: 'success'
				// 	})
				// }
				// if (data.code == 500) {
				// 	_this.$message({
				// 		showClose: true,
				// 		message: '系统错误',
				// 		type: 'error'
				// 	})
				// }

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
			async getData(Page) { //得到房屋数据
				if (Page != null && Page != 'undefined') {
					this.currentPage = Page
				} else {
					this.currentPage = 1
				}
				this.loading = true
				const _this = this

				let data = {
					pageNum: _this.currentPage,
					pageSize: _this.pageSize
				};
				if (_this.name != '') {
					data. ["name"] = _this.name
				}
				await axios.get(this.API.GetProperty, {
					params: data
				}).then(resp => {
					console.log(resp.data)
					_this.total = resp.data.result.total
					_this.tableData = resp.data.result.list
				})

				// let data = _this.requestSend(this.API.GetCategory,null,"get");
				// console.log(data);

				this.loading = false
			},
			page(currentPage) { //换页
				this.getData(currentPage)
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



			//图片上传

			async uploadImage(req) {
				const config = {
					headers: {
						'Content-Type': 'multipart/form-data'
					}
				};
				let filetype = ''
				if (req.file.type === 'image/png') {
					filetype = 'png'
				} else {
					filetype = 'jpg'
				}
				// const keyName = this.bucket +  "-" + Types.ObjectId().toString() +  '.' + fileType;
				const formdata = new FormData();
				formdata.append('picture', req.file);
				axios.post(this.API.UploadImage, formdata, config)
					.then(res => {
						this.form.fileList.push(res.data.result.filename);
						console.log('image upload succeed.');
					})
					.catch(err => {
						console.log(err.message)
					})
			},
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
