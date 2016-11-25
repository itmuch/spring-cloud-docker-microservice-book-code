var http = require('http');
var url = require("url");
var path = require('path');

// 创建server
var server = http.createServer(function(req, res) {
  // 获得请求的路径
  var pathname = url.parse(req.url).pathname;
  res.writeHead(200, { 'Content-Type' : 'application/json' });
  // 访问http://localhost:8070/，将会返回{"index":"欢迎来到首页"}
  if (pathname === '/') {
    res.end(JSON.stringify({ "index" : "欢迎来到首页" }));
  }
  // 访问http://localhost:8070/health，将会返回{"status":"UP"}
  else if (pathname === '/health') {
    res.end(JSON.stringify({ "status" : "UP" }));
  }
  // 其他情况返回404
  else {
    res.end("404");
  }
});
// 创建监听，并打印日志
server.listen(8070, function() {
  console.log('listening on localhost:8070');
});