import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.thrift.ThriftServerFramedCodec
import com.videoplaza.thrifty.ThriftyService
import java.net.InetSocketAddress
import java.util.concurrent.Future
import org.apache.thrift.protocol.TBinaryProtocol

import com.twitter.finagle.thrift.ThriftServerFramedCodec
import org.apache.thrift.protocol.TBinaryProtocol
import com.twitter.finagle.builder.{ServerBuilder, Server}
import java.net.InetSocketAddress
import com.twitter.util.Future
/**
 * Created by IntelliJ IDEA.
 * User: fredrik
 * Date: 2012-03-30
 * Time: 18:07
 */
object ThriftServer {
	def main(args: Array[String]) {
		// Implement the Thrift Interface
		val processor = new ThriftyService.ServiceIface {
			def hi() = Future.value("hi")
		}

		val service = new Hello.Service(processor, new TBinaryProtocol.Factory())

		val server: Server = ServerBuilder().name("HelloService").bindTo(new InetSocketAddress(8080)).codec(ThriftServerFramedCodec()).build(service)
	}
}