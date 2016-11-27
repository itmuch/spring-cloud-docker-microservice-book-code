import httplib

from twisted.web import server, resource
from twisted.internet import reactor, endpoints

class Health(resource.Resource):
    isLeaf = True

    def render_GET(self, request):
        request.setHeader("content-type", "application/json")
        return '{"status":"UP"}\n'

class Fortune(resource.Resource):
    isLeaf = True

    def render_GET(self, request):
        conn = httplib.HTTPConnection('localhost', 5678)
        conn.request("GET", "/fortunes")
        res = conn.getresponse()
        fortune = res.read()
        request.setHeader("content-type", "text/plain")
        return fortune


root = resource.Resource()
root.putChild('health', Health())
root.putChild('', Fortune())
endpoints.serverFromString(reactor, "tcp:5680").listen(server.Site(root))
reactor.run()

# FROM: https://gist.github.com/spencergibb/4f4b56c0e31c300531e9
# FROM: https://github.com/spencergibb/oscon2015