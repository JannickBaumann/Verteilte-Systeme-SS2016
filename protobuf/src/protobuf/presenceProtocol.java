// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Authentication.proto

package protobuf;

public final class presenceProtocol {
  private presenceProtocol() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SlaveAuthenticationOrBuilder extends
      // @@protoc_insertion_point(interface_extends:authenticator.SlaveAuthentication)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
     */
    boolean hasStatus();
    /**
     * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
     */
    protobuf.presenceProtocol.SlaveAuthentication.Status getStatus();
  }
  /**
   * Protobuf type {@code authenticator.SlaveAuthentication}
   */
  public static final class SlaveAuthentication extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:authenticator.SlaveAuthentication)
      SlaveAuthenticationOrBuilder {
    // Use SlaveAuthentication.newBuilder() to construct.
    private SlaveAuthentication(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SlaveAuthentication(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SlaveAuthentication defaultInstance;
    public static SlaveAuthentication getDefaultInstance() {
      return defaultInstance;
    }

    public SlaveAuthentication getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SlaveAuthentication(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();
              protobuf.presenceProtocol.SlaveAuthentication.Status value = protobuf.presenceProtocol.SlaveAuthentication.Status.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                status_ = value;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.presenceProtocol.internal_static_authenticator_SlaveAuthentication_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.presenceProtocol.internal_static_authenticator_SlaveAuthentication_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protobuf.presenceProtocol.SlaveAuthentication.class, protobuf.presenceProtocol.SlaveAuthentication.Builder.class);
    }

    public static com.google.protobuf.Parser<SlaveAuthentication> PARSER =
        new com.google.protobuf.AbstractParser<SlaveAuthentication>() {
      public SlaveAuthentication parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SlaveAuthentication(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SlaveAuthentication> getParserForType() {
      return PARSER;
    }

    /**
     * Protobuf enum {@code authenticator.SlaveAuthentication.Status}
     */
    public enum Status
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>OFF = 0;</code>
       */
      OFF(0, 0),
      /**
       * <code>ON = 1;</code>
       */
      ON(1, 1),
      ;

      /**
       * <code>OFF = 0;</code>
       */
      public static final int OFF_VALUE = 0;
      /**
       * <code>ON = 1;</code>
       */
      public static final int ON_VALUE = 1;


      public final int getNumber() { return value; }

      public static Status valueOf(int value) {
        switch (value) {
          case 0: return OFF;
          case 1: return ON;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Status>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static com.google.protobuf.Internal.EnumLiteMap<Status>
          internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Status>() {
              public Status findValueByNumber(int number) {
                return Status.valueOf(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(index);
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return protobuf.presenceProtocol.SlaveAuthentication.getDescriptor().getEnumTypes().get(0);
      }

      private static final Status[] VALUES = values();

      public static Status valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }

      private final int index;
      private final int value;

      private Status(int index, int value) {
        this.index = index;
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:authenticator.SlaveAuthentication.Status)
    }
    
    //ende enum status

    private int bitField0_;
    public static final int STATUS_FIELD_NUMBER = 1;
    private protobuf.presenceProtocol.SlaveAuthentication.Status status_;
    /**
     * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
     */
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
     */
    public protobuf.presenceProtocol.SlaveAuthentication.Status getStatus() {
      return status_;
    }

    private void initFields() {
      status_ = protobuf.presenceProtocol.SlaveAuthentication.Status.OFF;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeEnum(1, status_.getNumber());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, status_.getNumber());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static protobuf.presenceProtocol.SlaveAuthentication parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.presenceProtocol.SlaveAuthentication prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code authenticator.SlaveAuthentication}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:authenticator.SlaveAuthentication)
        protobuf.presenceProtocol.SlaveAuthenticationOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.presenceProtocol.internal_static_authenticator_SlaveAuthentication_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.presenceProtocol.internal_static_authenticator_SlaveAuthentication_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protobuf.presenceProtocol.SlaveAuthentication.class, protobuf.presenceProtocol.SlaveAuthentication.Builder.class);
      }

      // Construct using protobuf.presenceProtocol.SlaveAuthentication.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        status_ = protobuf.presenceProtocol.SlaveAuthentication.Status.OFF;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.presenceProtocol.internal_static_authenticator_SlaveAuthentication_descriptor;
      }

      public protobuf.presenceProtocol.SlaveAuthentication getDefaultInstanceForType() {
        return protobuf.presenceProtocol.SlaveAuthentication.getDefaultInstance();
      }

      public protobuf.presenceProtocol.SlaveAuthentication build() {
        protobuf.presenceProtocol.SlaveAuthentication result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protobuf.presenceProtocol.SlaveAuthentication buildPartial() {
        protobuf.presenceProtocol.SlaveAuthentication result = new protobuf.presenceProtocol.SlaveAuthentication(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.status_ = status_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.presenceProtocol.SlaveAuthentication) {
          return mergeFrom((protobuf.presenceProtocol.SlaveAuthentication)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protobuf.presenceProtocol.SlaveAuthentication other) {
        if (other == protobuf.presenceProtocol.SlaveAuthentication.getDefaultInstance()) return this;
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protobuf.presenceProtocol.SlaveAuthentication parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protobuf.presenceProtocol.SlaveAuthentication) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private protobuf.presenceProtocol.SlaveAuthentication.Status status_ = protobuf.presenceProtocol.SlaveAuthentication.Status.OFF;
      /**
       * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
       */
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
       */
      public protobuf.presenceProtocol.SlaveAuthentication.Status getStatus() {
        return status_;
      }
      /**
       * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
       */
      public Builder setStatus(protobuf.presenceProtocol.SlaveAuthentication.Status value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        status_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional .authenticator.SlaveAuthentication.Status status = 1;</code>
       */
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = protobuf.presenceProtocol.SlaveAuthentication.Status.OFF;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:authenticator.SlaveAuthentication)
    }

    static {
      defaultInstance = new SlaveAuthentication(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:authenticator.SlaveAuthentication)
  }

  public interface MasterAuthenticationOrBuilder extends
      // @@protoc_insertion_point(interface_extends:authenticator.MasterAuthentication)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required bool ack = 1;</code>
     */
    boolean hasAck();
    /**
     * <code>required bool ack = 1;</code>
     */
    boolean getAck();
  }
  /**
   * Protobuf type {@code authenticator.MasterAuthentication}
   */
  public static final class MasterAuthentication extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:authenticator.MasterAuthentication)
      MasterAuthenticationOrBuilder {
    // Use MasterAuthentication.newBuilder() to construct.
    private MasterAuthentication(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private MasterAuthentication(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final MasterAuthentication defaultInstance;
    public static MasterAuthentication getDefaultInstance() {
      return defaultInstance;
    }

    public MasterAuthentication getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private MasterAuthentication(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              ack_ = input.readBool();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.presenceProtocol.internal_static_authenticator_MasterAuthentication_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.presenceProtocol.internal_static_authenticator_MasterAuthentication_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protobuf.presenceProtocol.MasterAuthentication.class, protobuf.presenceProtocol.MasterAuthentication.Builder.class);
    }

    public static com.google.protobuf.Parser<MasterAuthentication> PARSER =
        new com.google.protobuf.AbstractParser<MasterAuthentication>() {
      public MasterAuthentication parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MasterAuthentication(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<MasterAuthentication> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ACK_FIELD_NUMBER = 1;
    private boolean ack_;
    /**
     * <code>required bool ack = 1;</code>
     */
    public boolean hasAck() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required bool ack = 1;</code>
     */
    public boolean getAck() {
      return ack_;
    }

    private void initFields() {
      ack_ = false;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasAck()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBool(1, ack_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, ack_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static protobuf.presenceProtocol.MasterAuthentication parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.presenceProtocol.MasterAuthentication prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code authenticator.MasterAuthentication}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:authenticator.MasterAuthentication)
        protobuf.presenceProtocol.MasterAuthenticationOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.presenceProtocol.internal_static_authenticator_MasterAuthentication_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.presenceProtocol.internal_static_authenticator_MasterAuthentication_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protobuf.presenceProtocol.MasterAuthentication.class, protobuf.presenceProtocol.MasterAuthentication.Builder.class);
      }

      // Construct using protobuf.presenceProtocol.MasterAuthentication.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        ack_ = false;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.presenceProtocol.internal_static_authenticator_MasterAuthentication_descriptor;
      }

      public protobuf.presenceProtocol.MasterAuthentication getDefaultInstanceForType() {
        return protobuf.presenceProtocol.MasterAuthentication.getDefaultInstance();
      }

      public protobuf.presenceProtocol.MasterAuthentication build() {
        protobuf.presenceProtocol.MasterAuthentication result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protobuf.presenceProtocol.MasterAuthentication buildPartial() {
        protobuf.presenceProtocol.MasterAuthentication result = new protobuf.presenceProtocol.MasterAuthentication(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.ack_ = ack_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.presenceProtocol.MasterAuthentication) {
          return mergeFrom((protobuf.presenceProtocol.MasterAuthentication)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protobuf.presenceProtocol.MasterAuthentication other) {
        if (other == protobuf.presenceProtocol.MasterAuthentication.getDefaultInstance()) return this;
        if (other.hasAck()) {
          setAck(other.getAck());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasAck()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protobuf.presenceProtocol.MasterAuthentication parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protobuf.presenceProtocol.MasterAuthentication) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private boolean ack_ ;
      /**
       * <code>required bool ack = 1;</code>
       */
      public boolean hasAck() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required bool ack = 1;</code>
       */
      public boolean getAck() {
        return ack_;
      }
      /**
       * <code>required bool ack = 1;</code>
       */
      public Builder setAck(boolean value) {
        bitField0_ |= 0x00000001;
        ack_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bool ack = 1;</code>
       */
      public Builder clearAck() {
        bitField0_ = (bitField0_ & ~0x00000001);
        ack_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:authenticator.MasterAuthentication)
    }

    static {
      defaultInstance = new MasterAuthentication(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:authenticator.MasterAuthentication)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_authenticator_SlaveAuthentication_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_authenticator_SlaveAuthentication_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_authenticator_MasterAuthentication_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_authenticator_MasterAuthentication_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024Authentication.proto\022\rauthenticator\"k\n" +
      "\023SlaveAuthentication\0229\n\006status\030\001 \001(\0162).a" +
      "uthenticator.SlaveAuthentication.Status\"" +
      "\031\n\006Status\022\007\n\003OFF\020\000\022\006\n\002ON\020\001\"#\n\024MasterAuth" +
      "entication\022\013\n\003ack\030\001 \002(\010B\034\n\010protobufB\020pre" +
      "senceProtocol"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_authenticator_SlaveAuthentication_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_authenticator_SlaveAuthentication_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_authenticator_SlaveAuthentication_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_authenticator_MasterAuthentication_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_authenticator_MasterAuthentication_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_authenticator_MasterAuthentication_descriptor,
        new java.lang.String[] { "Ack", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
