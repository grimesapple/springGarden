<!--房屋列表-->
<template>
	<article style="height: 90%">
		<div style="height: 50px;">
			<el-page-header content="房源管理">
			</el-page-header>
		</div>
		<div class="loading" v-loading="loading" v-show="loading"></div>
		<!--搜索-->
		<div class="search" v-if="operation=='seeAll'">
			<el-input class="inputS" placeholder="房间名称" v-model="subTitle" clearable>
			</el-input>
			<div style="margin-left: 20px;height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-search" size="mini" @click="getHouseData()">搜索</el-button>
			</div>
			<span>共{{total}}条搜索结果</span>
		</div>
		<!-- 新增 -->
		<!-- 		<div class="search" v-if="operation=='seeAll'">
			<div style="height: 40px;padding-top: 5px">
				<el-button type="primary" icon="el-icon-plus" size="mini"
					@click="dialogVisible = true,dialogType='add'">添加</el-button>
			</div>
		</div> -->

		<!--内容区域-->
		<el-table :data="tableData" height="90%" border stripe>
			<el-table-column prop="house.id" label="房屋id" width="70" align="center"></el-table-column>
			<el-table-column prop="house.name" label="房屋名字" align="center"></el-table-column>
			<el-table-column prop="house.subTitle" label="房屋标题" align="center"></el-table-column>
			<el-table-column label="房屋照片" width="215" align="center">
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
					<el-tag size="mini" v-for="(imgItem,key) in scope.row.property" :key="key"
						:type="key%2 === 1 ? 'primary' : 'success'" disable-transitions>{{imgItem.propertyName}}
					</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="house.content" :show-overflow-tooltip="true" label="房间描述" align="center">
				<template slot-scope="scope">
					<p v-html="scope.row.house.content"></p>
				</template>
			</el-table-column>
			<el-table-column label="操作" align="center">
				<template slot-scope="scope">
					<el-button @click=" set(form, scope.row)" type="text" size="small">修改</el-button>
					<el-popconfirm title="这是一段内容确定删除吗？" @confirm="del(scope.row)">
						<el-button type="text" size="small" slot="reference">删除</el-button>
					</el-popconfirm>
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

				<el-form-item label="上传图片">
					<el-upload class="upload-demo" ref="upload" :http-request=uploadImage list-type="picture-card"
						multiple>
						<i class="el-icon-plus"></i>
						<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
					</el-upload>
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
				/*表格数据*/
				tableData: [],
				/*每页显示数量*/
				pageSize: 5,
				/*房屋总数*/
				total: 0,
				/*当前页码*/
				currentPage: 1,
				/*整个页面是否显示加载图标*/
				loading: false,
				/*状态*/
				state: '',
				/*房屋标题*/
				subTitle: '',
				/*图片回显地址*/
				root: this.API.ShowImage,
				/*搜索条件：房屋标题*/
				subTitle: "",
				/*搜索条件：选择日期*/
				chooseDate: "",
				/*搜索条件：房屋标题*/
				subTitle: "",
				/*搜索条件：房屋标题*/
				subTitle: "",
				//添加弹出框
				dialogVisible: false,
				dialogType: "",
				/*表单数据*/
				form: {
					name: '大床房',
					subTitle: '浪漫',
					orignalPrice: '100',
					promotePrice: '80',
					stock: '1',
					categoryId: '1',
					//图片地址
					fileList: [],
				},
				//房间类型
				categorys: [],
				//图片上传地址
				actionurl: "",
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
			//修改
			set(form, row) {

				this.$router.push({
					path: "/HouseInfo/updateHouse",
					query: {
						data: JSON.stringify(row)
					}
				})
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
				//房子信息添加
				const _this = this;
				let datas = QS.stringify(_this.form)

				axios.post(_this.API.AddHouse, _this.form).then(res => {
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

				this.getHouseData()
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

			//删除
			async delHouse(row) {
				console.log(row.house.id);
				let data = {}
				data.id = row.house.id;
				let _this = this
				await axios.get(this.API.delHouse, {
					params: {
						id: row.house.id
					}
				}).then(resp => {
					// console.log(resp.data)
					// _this.total = resp.data.result.total
					// _this.tableData = resp.data.result.list
					let data = resp.data;
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

				// let data = _this.requestSend(this.API.GetCategory,null,"get");
				// console.log(data);
				// this.requestSend(this.API.delHouse, data, "get");
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

<style>
 .el-tooltip__popper{
    max-width:20%;
  }
  .el-tooltip__popper,.el-tooltip__popper.is-dark{
    background:rgb(48, 65, 86) !important;
    color: #fff !important;
    line-height: 24px;
  }
</style>

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

	.inputS {
		margin-left: 20px;
		height: 33px;
		width: 175px;
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
