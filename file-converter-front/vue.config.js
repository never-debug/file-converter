const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port:80,
    proxy:{
      '/api':{
        target: 'http://121.5.146.35:8888',  //请求的服务器地址
        pathRewrite:{'^/api':''},  //可以让发过去的请求不带/api打头
      }
    }
  }

})
