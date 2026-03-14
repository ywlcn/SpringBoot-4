package com.example.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class SampleGrpc {

  private SampleGrpc() {}

  public static final String SERVICE_NAME = "Sample";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SampleProto.HelloRequest,
      SampleProto.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = SampleProto.HelloRequest.class,
      responseType = SampleProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SampleProto.HelloRequest,
      SampleProto.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<SampleProto.HelloRequest, SampleProto.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = SampleGrpc.getSayHelloMethod) == null) {
      synchronized (SampleGrpc.class) {
        if ((getSayHelloMethod = SampleGrpc.getSayHelloMethod) == null) {
          SampleGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<SampleProto.HelloRequest, SampleProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SampleProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SampleProto.HelloReply.getDefaultInstance()))
              .setSchemaDescriptor(new SampleMethodDescriptorSupplier("SayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SampleProto.HelloRequest,
      SampleProto.HelloReply> getStreamHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamHello",
      requestType = SampleProto.HelloRequest.class,
      responseType = SampleProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<SampleProto.HelloRequest,
      SampleProto.HelloReply> getStreamHelloMethod() {
    io.grpc.MethodDescriptor<SampleProto.HelloRequest, SampleProto.HelloReply> getStreamHelloMethod;
    if ((getStreamHelloMethod = SampleGrpc.getStreamHelloMethod) == null) {
      synchronized (SampleGrpc.class) {
        if ((getStreamHelloMethod = SampleGrpc.getStreamHelloMethod) == null) {
          SampleGrpc.getStreamHelloMethod = getStreamHelloMethod =
              io.grpc.MethodDescriptor.<SampleProto.HelloRequest, SampleProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SampleProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SampleProto.HelloReply.getDefaultInstance()))
              .setSchemaDescriptor(new SampleMethodDescriptorSupplier("StreamHello"))
              .build();
        }
      }
    }
    return getStreamHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SampleStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SampleStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SampleStub>() {
        @Override
        public SampleStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SampleStub(channel, callOptions);
        }
      };
    return SampleStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SampleBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SampleBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SampleBlockingV2Stub>() {
        @Override
        public SampleBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SampleBlockingV2Stub(channel, callOptions);
        }
      };
    return SampleBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SampleBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SampleBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SampleBlockingStub>() {
        @Override
        public SampleBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SampleBlockingStub(channel, callOptions);
        }
      };
    return SampleBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SampleFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SampleFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SampleFutureStub>() {
        @Override
        public SampleFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SampleFutureStub(channel, callOptions);
        }
      };
    return SampleFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    default void sayHello(SampleProto.HelloRequest request,
                          io.grpc.stub.StreamObserver<SampleProto.HelloReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     */
    default void streamHello(SampleProto.HelloRequest request,
                             io.grpc.stub.StreamObserver<SampleProto.HelloReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamHelloMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Sample.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class SampleImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return SampleGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Sample.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SampleStub
      extends io.grpc.stub.AbstractAsyncStub<SampleStub> {
    private SampleStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SampleStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SampleStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void sayHello(SampleProto.HelloRequest request,
                         io.grpc.stub.StreamObserver<SampleProto.HelloReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamHello(SampleProto.HelloRequest request,
                            io.grpc.stub.StreamObserver<SampleProto.HelloReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Sample.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SampleBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SampleBlockingV2Stub> {
    private SampleBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SampleBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SampleBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public SampleProto.HelloReply sayHello(SampleProto.HelloRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, SampleProto.HelloReply>
        streamHello(SampleProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getStreamHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service Sample.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SampleBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SampleBlockingStub> {
    private SampleBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SampleBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SampleBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public SampleProto.HelloReply sayHello(SampleProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<SampleProto.HelloReply> streamHello(
        SampleProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Sample.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SampleFutureStub
      extends io.grpc.stub.AbstractFutureStub<SampleFutureStub> {
    private SampleFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SampleFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SampleFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<SampleProto.HelloReply> sayHello(
        SampleProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_STREAM_HELLO = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((SampleProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<SampleProto.HelloReply>) responseObserver);
          break;
        case METHODID_STREAM_HELLO:
          serviceImpl.streamHello((SampleProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<SampleProto.HelloReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSayHelloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              SampleProto.HelloRequest,
              SampleProto.HelloReply>(
                service, METHODID_SAY_HELLO)))
        .addMethod(
          getStreamHelloMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              SampleProto.HelloRequest,
              SampleProto.HelloReply>(
                service, METHODID_STREAM_HELLO)))
        .build();
  }

  private static abstract class SampleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SampleBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SampleProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Sample");
    }
  }

  private static final class SampleFileDescriptorSupplier
      extends SampleBaseDescriptorSupplier {
    SampleFileDescriptorSupplier() {}
  }

  private static final class SampleMethodDescriptorSupplier
      extends SampleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SampleMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SampleGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SampleFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getStreamHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
