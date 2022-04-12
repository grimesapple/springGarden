<template>
	<section>
		<el-menu class="el-menu-demo" mode="horizontal" background-color="#edf4ed" text-color="#111111"
			active-text-color="#41ac52" style="display: flex;justify-content: flex-end;">
			<h2 class="title">欢迎青庭民宿管理员&nbsp; {{$store.state.userInfo.username}}</h2>
			<!--  <el-menu-item index="1"><a href="/">主页</a></el-menu-item>-->
			<el-menu-item index="1">
				<el-dropdown trigger="click">
					<!-- 					<div class="el-dropdown-link header-avatar">
						<img :src="$store.state.userInfo.headImg">
					</div> -->
					<div class="el-dropdown-link">
						<span>{{$store.state.userInfo.username}} </span>
					</div>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item @click.native="loginOut">退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-menu-item>
			<el-menu-item index="2">
				<router-link to="/">回到主页</router-link>
			</el-menu-item>
		</el-menu>
		<el-container style="height: 900px; border-bottom: 1px solid #eee">
			<!--侧边栏-->
			<el-aside width="201px" style="background-color: rgb(238, 241, 246)">
				<el-menu router :default-openeds="['0','1']" background-color="#edf4ed" text-color="#111111"
					active-text-color="#005b43">
					<!--默认打开菜单0,1- -->
					<!-- 					<el-submenu index="0">
						<template slot="title">
							<i class="el-icon-menu menustyle"></i>
							<span>房源管理</span>
						</template>				
					</el-submenu> -->
					<!-- 					<el-menu-item index="/HouseInfo/addHouse"
						:class="$route.path=='/HouseInfo/addHouse'?'is-active':''">
						<i class="el-icon-user"></i>添加房源
					</el-menu-item> -->
					<el-menu-item index="/HouseList/seeAll" :class="$route.path=='/HouseList/seeAll'?'is-active':''">
						<i class="el-icon-s-home"></i>房源管理
					</el-menu-item>
					<el-menu-item index="/Property" :class="$route.path=='/Property '?'is-active':''">
						<i class="el-icon-s-home"></i>设施管理
					</el-menu-item>
					<!-- 					<el-submenu index="2">
						<template slot="title">
							<i class="el-icon-menu"></i>
							<span>用户模块</span>
						</template>
						<el-menu-item index="/UserList" :class="$route.path=='/UserList'?'is-active':''">
							<i class="el-icon-document"></i>用户管理
						</el-menu-item>
					</el-submenu> -->
					<el-menu-item index="/UserList" :class="$route.path=='/UserList'?'is-active':''">
						<i class="el-icon-document"></i>用户管理
					</el-menu-item>
					<el-submenu index="1">
						<template slot="title">
							<i class="el-icon-menu"></i>
							<span>订单模块</span>
						</template>
						<el-menu-item index="/OrderListInfo/add"
							:class="$route.path=='/OrderListInfo/add'?'is-active':''">
							<i class="el-icon-document"></i>添加订单
						</el-menu-item>
						<el-menu-item index="/OrderList/seeAll"
							:class="$route.path=='/OrderList/seeAll'?'is-active':''">
							<i class="el-icon-document"></i>订单管理
						</el-menu-item>
					</el-submenu>
					<!-- 					<el-submenu index="3">
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
					</el-submenu> -->
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
				this.$store.state.isLogin = false
				this.$store.state.userInfo = []
				this.$store.state.token = ''
				this.notice = []
				localStorage.setItem('store', null)
				if (this.$route.path != '/') {
					await this.$router.push({
						path: "/",
					})
				}
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
