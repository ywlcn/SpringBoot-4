package com.example.interceptor;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExtraThingsInterceptor  implements ClientInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExtraThingsInterceptor.class);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {

        // 1. 実際の通信を行うCallオブジェクトを生成し、ラップする
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

            @Override
            public void sendMessage(ReqT message) {
                // リクエスト送信時のログ
                logger.info("gRPC送信: メソッド=" + method.getFullMethodName() + ", リクエスト=" + message);
                super.sendMessage(message);
            }

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                // 2. レスポンスを受け取るリスナーもラップする
                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onMessage(RespT message) {
                        // レスポンス受信時のログ
                        logger.info("gRPC受信: メソッド=" + method.getFullMethodName() + ", レスポンス=" + message);
                        super.onMessage(message);
                    }

                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        // 通信終了時のログ（エラー確認など）
                        if (!status.isOk()) {
                            logger.info("gRPCエラー: ステータス=" + status.getCode() + ", 説明=" + status.getDescription());
                        }
                        super.onClose(status, trailers);
                    }
                }, headers);
            }
        };
    }
}