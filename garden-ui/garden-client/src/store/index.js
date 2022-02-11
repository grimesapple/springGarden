import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo:[],
    isLogin:false,
	token:""
  },
  mutations: {//存全局的同步方法
    getInitData(state){
    },
	setToken(){};
  },
  actions: {
  },
  modules: {
  }
})
