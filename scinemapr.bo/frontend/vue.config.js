module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8087',
        secure: false,
        changeOrigin: true,
        publicPath: {
          '^/api': '/'
        }
      }
    }
  },
  outputDir: '../src/main/resources/static',
  indexPath: '../static/index.html',
  //assetsDir: '../static'
  transpileDependencies: [
    'vuetify'
  ]
}
