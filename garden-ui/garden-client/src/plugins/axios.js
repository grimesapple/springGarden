"use strict";

import Vue from 'vue';
import store from '../store'
import axios from "axios";
import Element from './element.js'

// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
	// baseURL: process.env.baseURL || process.env.apiUrl || ""
	// timeout: 60 * 1000, // Timeout
	// withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
	function(config) {
		// Do something before request is sent
		if (store.state.token) {
			config.headers['Authorization'] = store.state.token
		}
		return config;
	},
	function(error) {
		// Do something with request error
		return Promise.reject(error);
	}
);

// Add a response interceptor
_axios.interceptors.response.use(
	function(response) {
		// Do something with response data
		let result = response.data;
		if (result.code == 100) {
			//未登录、或者登录失效,清空token
			store.commit('setToken','');
			localStorage.removeItem("token");
			Element.message.error("登录失效")
			return Promise.reject("登录失效");
		}
		return response;
	},
	function(error) {
		// Do something with response error
		return Promise.reject(error);
	}
);

Plugin.install = function(Vue, options) {
	Vue.axios = _axios;
	window.axios = _axios;
	Object.defineProperties(Vue.prototype, {
		axios: {
			get() {
				return _axios;
			}
		},
		$axios: {
			get() {
				return _axios;
			}
		},
	});
};

Vue.use(Plugin)

export default Plugin;
