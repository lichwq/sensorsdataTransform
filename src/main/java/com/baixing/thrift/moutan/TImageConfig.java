/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.baixing.thrift.moutan;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-06-13")
public class TImageConfig implements org.apache.thrift.TBase<TImageConfig, TImageConfig._Fields>, java.io.Serializable, Cloneable, Comparable<TImageConfig> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TImageConfig");

  private static final org.apache.thrift.protocol.TField VENDOR_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("vendorName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField IMAGE_SUFFIX_FIELD_DESC = new org.apache.thrift.protocol.TField("imageSuffix", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField UPLOAD_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("uploadUrl", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField UPLOAD_PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("uploadParams", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField IMAGE_HOST_FIELD_DESC = new org.apache.thrift.protocol.TField("imageHost", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TImageConfigStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TImageConfigTupleSchemeFactory();

  public java.lang.String vendorName; // required
  public java.lang.String imageSuffix; // required
  public java.lang.String uploadUrl; // required
  public TUploadParams uploadParams; // required
  public java.lang.String imageHost; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    VENDOR_NAME((short)1, "vendorName"),
    IMAGE_SUFFIX((short)2, "imageSuffix"),
    UPLOAD_URL((short)3, "uploadUrl"),
    UPLOAD_PARAMS((short)4, "uploadParams"),
    IMAGE_HOST((short)5, "imageHost");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // VENDOR_NAME
          return VENDOR_NAME;
        case 2: // IMAGE_SUFFIX
          return IMAGE_SUFFIX;
        case 3: // UPLOAD_URL
          return UPLOAD_URL;
        case 4: // UPLOAD_PARAMS
          return UPLOAD_PARAMS;
        case 5: // IMAGE_HOST
          return IMAGE_HOST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.IMAGE_HOST};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.VENDOR_NAME, new org.apache.thrift.meta_data.FieldMetaData("vendorName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IMAGE_SUFFIX, new org.apache.thrift.meta_data.FieldMetaData("imageSuffix", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UPLOAD_URL, new org.apache.thrift.meta_data.FieldMetaData("uploadUrl", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UPLOAD_PARAMS, new org.apache.thrift.meta_data.FieldMetaData("uploadParams", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TUploadParams.class)));
    tmpMap.put(_Fields.IMAGE_HOST, new org.apache.thrift.meta_data.FieldMetaData("imageHost", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TImageConfig.class, metaDataMap);
  }

  public TImageConfig() {
  }

  public TImageConfig(
    java.lang.String vendorName,
    java.lang.String imageSuffix,
    java.lang.String uploadUrl,
    TUploadParams uploadParams)
  {
    this();
    this.vendorName = vendorName;
    this.imageSuffix = imageSuffix;
    this.uploadUrl = uploadUrl;
    this.uploadParams = uploadParams;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TImageConfig(TImageConfig other) {
    if (other.isSetVendorName()) {
      this.vendorName = other.vendorName;
    }
    if (other.isSetImageSuffix()) {
      this.imageSuffix = other.imageSuffix;
    }
    if (other.isSetUploadUrl()) {
      this.uploadUrl = other.uploadUrl;
    }
    if (other.isSetUploadParams()) {
      this.uploadParams = new TUploadParams(other.uploadParams);
    }
    if (other.isSetImageHost()) {
      this.imageHost = other.imageHost;
    }
  }

  public TImageConfig deepCopy() {
    return new TImageConfig(this);
  }

  @Override
  public void clear() {
    this.vendorName = null;
    this.imageSuffix = null;
    this.uploadUrl = null;
    this.uploadParams = null;
    this.imageHost = null;
  }

  public java.lang.String getVendorName() {
    return this.vendorName;
  }

  public TImageConfig setVendorName(java.lang.String vendorName) {
    this.vendorName = vendorName;
    return this;
  }

  public void unsetVendorName() {
    this.vendorName = null;
  }

  /** Returns true if field vendorName is set (has been assigned a value) and false otherwise */
  public boolean isSetVendorName() {
    return this.vendorName != null;
  }

  public void setVendorNameIsSet(boolean value) {
    if (!value) {
      this.vendorName = null;
    }
  }

  public java.lang.String getImageSuffix() {
    return this.imageSuffix;
  }

  public TImageConfig setImageSuffix(java.lang.String imageSuffix) {
    this.imageSuffix = imageSuffix;
    return this;
  }

  public void unsetImageSuffix() {
    this.imageSuffix = null;
  }

  /** Returns true if field imageSuffix is set (has been assigned a value) and false otherwise */
  public boolean isSetImageSuffix() {
    return this.imageSuffix != null;
  }

  public void setImageSuffixIsSet(boolean value) {
    if (!value) {
      this.imageSuffix = null;
    }
  }

  public java.lang.String getUploadUrl() {
    return this.uploadUrl;
  }

  public TImageConfig setUploadUrl(java.lang.String uploadUrl) {
    this.uploadUrl = uploadUrl;
    return this;
  }

  public void unsetUploadUrl() {
    this.uploadUrl = null;
  }

  /** Returns true if field uploadUrl is set (has been assigned a value) and false otherwise */
  public boolean isSetUploadUrl() {
    return this.uploadUrl != null;
  }

  public void setUploadUrlIsSet(boolean value) {
    if (!value) {
      this.uploadUrl = null;
    }
  }

  public TUploadParams getUploadParams() {
    return this.uploadParams;
  }

  public TImageConfig setUploadParams(TUploadParams uploadParams) {
    this.uploadParams = uploadParams;
    return this;
  }

  public void unsetUploadParams() {
    this.uploadParams = null;
  }

  /** Returns true if field uploadParams is set (has been assigned a value) and false otherwise */
  public boolean isSetUploadParams() {
    return this.uploadParams != null;
  }

  public void setUploadParamsIsSet(boolean value) {
    if (!value) {
      this.uploadParams = null;
    }
  }

  public java.lang.String getImageHost() {
    return this.imageHost;
  }

  public TImageConfig setImageHost(java.lang.String imageHost) {
    this.imageHost = imageHost;
    return this;
  }

  public void unsetImageHost() {
    this.imageHost = null;
  }

  /** Returns true if field imageHost is set (has been assigned a value) and false otherwise */
  public boolean isSetImageHost() {
    return this.imageHost != null;
  }

  public void setImageHostIsSet(boolean value) {
    if (!value) {
      this.imageHost = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case VENDOR_NAME:
      if (value == null) {
        unsetVendorName();
      } else {
        setVendorName((java.lang.String)value);
      }
      break;

    case IMAGE_SUFFIX:
      if (value == null) {
        unsetImageSuffix();
      } else {
        setImageSuffix((java.lang.String)value);
      }
      break;

    case UPLOAD_URL:
      if (value == null) {
        unsetUploadUrl();
      } else {
        setUploadUrl((java.lang.String)value);
      }
      break;

    case UPLOAD_PARAMS:
      if (value == null) {
        unsetUploadParams();
      } else {
        setUploadParams((TUploadParams)value);
      }
      break;

    case IMAGE_HOST:
      if (value == null) {
        unsetImageHost();
      } else {
        setImageHost((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case VENDOR_NAME:
      return getVendorName();

    case IMAGE_SUFFIX:
      return getImageSuffix();

    case UPLOAD_URL:
      return getUploadUrl();

    case UPLOAD_PARAMS:
      return getUploadParams();

    case IMAGE_HOST:
      return getImageHost();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case VENDOR_NAME:
      return isSetVendorName();
    case IMAGE_SUFFIX:
      return isSetImageSuffix();
    case UPLOAD_URL:
      return isSetUploadUrl();
    case UPLOAD_PARAMS:
      return isSetUploadParams();
    case IMAGE_HOST:
      return isSetImageHost();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TImageConfig)
      return this.equals((TImageConfig)that);
    return false;
  }

  public boolean equals(TImageConfig that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_vendorName = true && this.isSetVendorName();
    boolean that_present_vendorName = true && that.isSetVendorName();
    if (this_present_vendorName || that_present_vendorName) {
      if (!(this_present_vendorName && that_present_vendorName))
        return false;
      if (!this.vendorName.equals(that.vendorName))
        return false;
    }

    boolean this_present_imageSuffix = true && this.isSetImageSuffix();
    boolean that_present_imageSuffix = true && that.isSetImageSuffix();
    if (this_present_imageSuffix || that_present_imageSuffix) {
      if (!(this_present_imageSuffix && that_present_imageSuffix))
        return false;
      if (!this.imageSuffix.equals(that.imageSuffix))
        return false;
    }

    boolean this_present_uploadUrl = true && this.isSetUploadUrl();
    boolean that_present_uploadUrl = true && that.isSetUploadUrl();
    if (this_present_uploadUrl || that_present_uploadUrl) {
      if (!(this_present_uploadUrl && that_present_uploadUrl))
        return false;
      if (!this.uploadUrl.equals(that.uploadUrl))
        return false;
    }

    boolean this_present_uploadParams = true && this.isSetUploadParams();
    boolean that_present_uploadParams = true && that.isSetUploadParams();
    if (this_present_uploadParams || that_present_uploadParams) {
      if (!(this_present_uploadParams && that_present_uploadParams))
        return false;
      if (!this.uploadParams.equals(that.uploadParams))
        return false;
    }

    boolean this_present_imageHost = true && this.isSetImageHost();
    boolean that_present_imageHost = true && that.isSetImageHost();
    if (this_present_imageHost || that_present_imageHost) {
      if (!(this_present_imageHost && that_present_imageHost))
        return false;
      if (!this.imageHost.equals(that.imageHost))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetVendorName()) ? 131071 : 524287);
    if (isSetVendorName())
      hashCode = hashCode * 8191 + vendorName.hashCode();

    hashCode = hashCode * 8191 + ((isSetImageSuffix()) ? 131071 : 524287);
    if (isSetImageSuffix())
      hashCode = hashCode * 8191 + imageSuffix.hashCode();

    hashCode = hashCode * 8191 + ((isSetUploadUrl()) ? 131071 : 524287);
    if (isSetUploadUrl())
      hashCode = hashCode * 8191 + uploadUrl.hashCode();

    hashCode = hashCode * 8191 + ((isSetUploadParams()) ? 131071 : 524287);
    if (isSetUploadParams())
      hashCode = hashCode * 8191 + uploadParams.hashCode();

    hashCode = hashCode * 8191 + ((isSetImageHost()) ? 131071 : 524287);
    if (isSetImageHost())
      hashCode = hashCode * 8191 + imageHost.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TImageConfig other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetVendorName()).compareTo(other.isSetVendorName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVendorName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vendorName, other.vendorName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetImageSuffix()).compareTo(other.isSetImageSuffix());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetImageSuffix()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.imageSuffix, other.imageSuffix);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUploadUrl()).compareTo(other.isSetUploadUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUploadUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uploadUrl, other.uploadUrl);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUploadParams()).compareTo(other.isSetUploadParams());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUploadParams()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uploadParams, other.uploadParams);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetImageHost()).compareTo(other.isSetImageHost());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetImageHost()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.imageHost, other.imageHost);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TImageConfig(");
    boolean first = true;

    sb.append("vendorName:");
    if (this.vendorName == null) {
      sb.append("null");
    } else {
      sb.append(this.vendorName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("imageSuffix:");
    if (this.imageSuffix == null) {
      sb.append("null");
    } else {
      sb.append(this.imageSuffix);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("uploadUrl:");
    if (this.uploadUrl == null) {
      sb.append("null");
    } else {
      sb.append(this.uploadUrl);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("uploadParams:");
    if (this.uploadParams == null) {
      sb.append("null");
    } else {
      sb.append(this.uploadParams);
    }
    first = false;
    if (isSetImageHost()) {
      if (!first) sb.append(", ");
      sb.append("imageHost:");
      if (this.imageHost == null) {
        sb.append("null");
      } else {
        sb.append(this.imageHost);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (vendorName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'vendorName' was not present! Struct: " + toString());
    }
    if (imageSuffix == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'imageSuffix' was not present! Struct: " + toString());
    }
    if (uploadUrl == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uploadUrl' was not present! Struct: " + toString());
    }
    if (uploadParams == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uploadParams' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (uploadParams != null) {
      uploadParams.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TImageConfigStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TImageConfigStandardScheme getScheme() {
      return new TImageConfigStandardScheme();
    }
  }

  private static class TImageConfigStandardScheme extends org.apache.thrift.scheme.StandardScheme<TImageConfig> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TImageConfig struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // VENDOR_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.vendorName = iprot.readString();
              struct.setVendorNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IMAGE_SUFFIX
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.imageSuffix = iprot.readString();
              struct.setImageSuffixIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // UPLOAD_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uploadUrl = iprot.readString();
              struct.setUploadUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // UPLOAD_PARAMS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.uploadParams = new TUploadParams();
              struct.uploadParams.read(iprot);
              struct.setUploadParamsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // IMAGE_HOST
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.imageHost = iprot.readString();
              struct.setImageHostIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TImageConfig struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.vendorName != null) {
        oprot.writeFieldBegin(VENDOR_NAME_FIELD_DESC);
        oprot.writeString(struct.vendorName);
        oprot.writeFieldEnd();
      }
      if (struct.imageSuffix != null) {
        oprot.writeFieldBegin(IMAGE_SUFFIX_FIELD_DESC);
        oprot.writeString(struct.imageSuffix);
        oprot.writeFieldEnd();
      }
      if (struct.uploadUrl != null) {
        oprot.writeFieldBegin(UPLOAD_URL_FIELD_DESC);
        oprot.writeString(struct.uploadUrl);
        oprot.writeFieldEnd();
      }
      if (struct.uploadParams != null) {
        oprot.writeFieldBegin(UPLOAD_PARAMS_FIELD_DESC);
        struct.uploadParams.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.imageHost != null) {
        if (struct.isSetImageHost()) {
          oprot.writeFieldBegin(IMAGE_HOST_FIELD_DESC);
          oprot.writeString(struct.imageHost);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TImageConfigTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TImageConfigTupleScheme getScheme() {
      return new TImageConfigTupleScheme();
    }
  }

  private static class TImageConfigTupleScheme extends org.apache.thrift.scheme.TupleScheme<TImageConfig> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TImageConfig struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.vendorName);
      oprot.writeString(struct.imageSuffix);
      oprot.writeString(struct.uploadUrl);
      struct.uploadParams.write(oprot);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetImageHost()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetImageHost()) {
        oprot.writeString(struct.imageHost);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TImageConfig struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.vendorName = iprot.readString();
      struct.setVendorNameIsSet(true);
      struct.imageSuffix = iprot.readString();
      struct.setImageSuffixIsSet(true);
      struct.uploadUrl = iprot.readString();
      struct.setUploadUrlIsSet(true);
      struct.uploadParams = new TUploadParams();
      struct.uploadParams.read(iprot);
      struct.setUploadParamsIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.imageHost = iprot.readString();
        struct.setImageHostIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

