<template>
	<section>
		<el-menu class="el-menu-demo" mode="horizontal" background-color="#edf4ed" text-color="#111111"
			active-text-color="#ffd04b" style="display: flex;justify-content: flex-end;">
			<h2 class="title">欢迎青庭民宿管理员&nbsp; {{$store.state.userInfo.username}}</h2>
			<!--  <el-menu-item index="1"><a href="/">主页</a></el-menu-item>-->
			<el-menu-item index="1">
				<el-dropdown trigger="click">
					<div class="el-dropdown-link header-avatar">
						<img :src="$store.state.userInfo.headImg">
					</div>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item @click.native="loginOut">退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-menu-item>
			<el-menu-item index="2">
				<router-link to="/">回到主页</router-link>
			</el-menu-item>
			<el-menu-item index="3">
				<router-link to="/MerchantManage">商户系统</router-link>
			</el-menu-item>
		</el-menu>
		<el-container style="height: 900px; border-bottom: 1px solid #eee">
			<!--侧边栏-->
			<el-aside width="201px" style="background-color: rgb(238, 241, 246)">
				<el-menu router :default-openeds="['0','1']" background-color="#edf4ed" text-color="#111111"
					active-text-color="#005b43">
					<!--默认打开菜单0,1- -->
					<el-submenu index="0">
						<template slot="title">
							<i class="el-icon-menu"></i>
							<span>房源管理</span>
						</template>
	<!-- 					<el-menu-item index="/HouseList/examine"
							:class="$route.path=='/HouseList/examine'?'is-active':''">
							<i class="el-icon-user"></i>房源审核
						</el-menu-item> -->
						<el-menu-item index="/HouseInfo/addHouse"
							:class="$route.path=='/HouseInfo/addHouse'?'is-active':''">
							<i class="el-icon-user"></i>添加房源
						</el-menu-item>
						<el-menu-item index="/HouseList/seeAll"
							:class="$route.path=='/HouseList/seeAll'?'is-active':''">
							<i class="el-icon-s-home"></i>所有房源
						</el-menu-item>
						<el-menu-item index="/Property"
							:class="$route.path=='/Property '?'is-active':''">
							<i class="el-icon-s-home"></i>类型属性
						</el-menu-item>
						
					</el-submenu>
					<el-submenu index="1">
						<template slot="title">
							<i class="el-icon-menu"></i>
							<span>发布通知</span>
						</template>
						<el-menu-item index="/Notice/all" :class="$route.path=='/Notice/all'?'is-active':''">
							<i class="el-icon-s-promotion"></i>群发通知
						</el-menu-item>
						<el-menu-item index="/Notice/single" :class="$route.path=='/Notice/single'?'is-active':''">
							<i class="el-icon-s-custom"></i>用户通知
						</el-menu-item>
					</el-submenu>
				</el-menu>
			</el-aside>
			<!-- 内容区域 -->
			<el-main>
				<router-view :key="key"></router-view>
			</el-main>
		</el-container>
	</section>
</template>

<script>
	export default {
		components: {},
		data() {
			return {}
		},
		created() {
			//console.log(this.$router.options.routes)
		},
		methods: {
			async loginOut() { //退出登录
				//修改用户在线状态
				let user;
				await axios.get(this.API.Login + this.$store.state.userInfo.username + '/null/null').then((resp) => {
					user = resp.data
				})
				user.isOnline = 'false'
				await axios.put(this.API.UpdateUser, user)
				//清除数据
				this.$store.state.isLogin = false
				this.$store.state.userInfo = []
				sessionStorage.setItem('store', null)
				await this.$router.push({
					path: "/",
				})
			},
		},
		computed: {
			key() {
				return this.$route.fullPath
			}
		}
	}
</script>

<style scoped>
	.header-avatar {
		width: 32px;
		height: 32px;
		border-radius: 50%;
		overflow: hidden;
	}

	img {
		display: block;
		width: 100%;
	}

	.title {
		margin-right: 30%;
		color: #2e963d;
	}
</style>
