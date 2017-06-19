/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.baixing.thrift.moutan;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-06-13")
public class TDashboardInfo implements org.apache.thrift.TBase<TDashboardInfo, TDashboardInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TDashboardInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TDashboardInfo");

  private static final org.apache.thrift.protocol.TField ALIPAY_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("alipayInfo", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField LAST_DAY_INCOME_FIELD_DESC = new org.apache.thrift.protocol.TField("lastDayIncome", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField BALANCE_FIELD_DESC = new org.apache.thrift.protocol.TField("balance", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField DETAIL_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("detailCount", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField DETAIL_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("detailList", org.apache.thrift.protocol.TType.LIST, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TDashboardInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TDashboardInfoTupleSchemeFactory();

  public TAlipay alipayInfo; // required
  public int lastDayIncome; // required
  public int balance; // required
  public int detailCount; // required
  public java.util.List<TAccountDetail> detailList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ALIPAY_INFO((short)1, "alipayInfo"),
    LAST_DAY_INCOME((short)2, "lastDayIncome"),
    BALANCE((short)3, "balance"),
    DETAIL_COUNT((short)4, "detailCount"),
    DETAIL_LIST((short)5, "detailList");

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
        case 1: // ALIPAY_INFO
          return ALIPAY_INFO;
        case 2: // LAST_DAY_INCOME
          return LAST_DAY_INCOME;
        case 3: // BALANCE
          return BALANCE;
        case 4: // DETAIL_COUNT
          return DETAIL_COUNT;
        case 5: // DETAIL_LIST
          return DETAIL_LIST;
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
  private static final int __LASTDAYINCOME_ISSET_ID = 0;
  private static final int __BALANCE_ISSET_ID = 1;
  private static final int __DETAILCOUNT_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ALIPAY_INFO, new org.apache.thrift.meta_data.FieldMetaData("alipayInfo", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TAlipay.class)));
    tmpMap.put(_Fields.LAST_DAY_INCOME, new org.apache.thrift.meta_data.FieldMetaData("lastDayIncome", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.BALANCE, new org.apache.thrift.meta_data.FieldMetaData("balance", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DETAIL_COUNT, new org.apache.thrift.meta_data.FieldMetaData("detailCount", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DETAIL_LIST, new org.apache.thrift.meta_data.FieldMetaData("detailList", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TAccountDetail.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TDashboardInfo.class, metaDataMap);
  }

  public TDashboardInfo() {
  }

  public TDashboardInfo(
    TAlipay alipayInfo,
    int lastDayIncome,
    int balance,
    int detailCount,
    java.util.List<TAccountDetail> detailList)
  {
    this();
    this.alipayInfo = alipayInfo;
    this.lastDayIncome = lastDayIncome;
    setLastDayIncomeIsSet(true);
    this.balance = balance;
    setBalanceIsSet(true);
    this.detailCount = detailCount;
    setDetailCountIsSet(true);
    this.detailList = detailList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TDashboardInfo(TDashboardInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAlipayInfo()) {
      this.alipayInfo = new TAlipay(other.alipayInfo);
    }
    this.lastDayIncome = other.lastDayIncome;
    this.balance = other.balance;
    this.detailCount = other.detailCount;
    if (other.isSetDetailList()) {
      java.util.List<TAccountDetail> __this__detailList = new java.util.ArrayList<TAccountDetail>(other.detailList.size());
      for (TAccountDetail other_element : other.detailList) {
        __this__detailList.add(new TAccountDetail(other_element));
      }
      this.detailList = __this__detailList;
    }
  }

  public TDashboardInfo deepCopy() {
    return new TDashboardInfo(this);
  }

  @Override
  public void clear() {
    this.alipayInfo = null;
    setLastDayIncomeIsSet(false);
    this.lastDayIncome = 0;
    setBalanceIsSet(false);
    this.balance = 0;
    setDetailCountIsSet(false);
    this.detailCount = 0;
    this.detailList = null;
  }

  public TAlipay getAlipayInfo() {
    return this.alipayInfo;
  }

  public TDashboardInfo setAlipayInfo(TAlipay alipayInfo) {
    this.alipayInfo = alipayInfo;
    return this;
  }

  public void unsetAlipayInfo() {
    this.alipayInfo = null;
  }

  /** Returns true if field alipayInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetAlipayInfo() {
    return this.alipayInfo != null;
  }

  public void setAlipayInfoIsSet(boolean value) {
    if (!value) {
      this.alipayInfo = null;
    }
  }

  public int getLastDayIncome() {
    return this.lastDayIncome;
  }

  public TDashboardInfo setLastDayIncome(int lastDayIncome) {
    this.lastDayIncome = lastDayIncome;
    setLastDayIncomeIsSet(true);
    return this;
  }

  public void unsetLastDayIncome() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LASTDAYINCOME_ISSET_ID);
  }

  /** Returns true if field lastDayIncome is set (has been assigned a value) and false otherwise */
  public boolean isSetLastDayIncome() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LASTDAYINCOME_ISSET_ID);
  }

  public void setLastDayIncomeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LASTDAYINCOME_ISSET_ID, value);
  }

  public int getBalance() {
    return this.balance;
  }

  public TDashboardInfo setBalance(int balance) {
    this.balance = balance;
    setBalanceIsSet(true);
    return this;
  }

  public void unsetBalance() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __BALANCE_ISSET_ID);
  }

  /** Returns true if field balance is set (has been assigned a value) and false otherwise */
  public boolean isSetBalance() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __BALANCE_ISSET_ID);
  }

  public void setBalanceIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __BALANCE_ISSET_ID, value);
  }

  public int getDetailCount() {
    return this.detailCount;
  }

  public TDashboardInfo setDetailCount(int detailCount) {
    this.detailCount = detailCount;
    setDetailCountIsSet(true);
    return this;
  }

  public void unsetDetailCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DETAILCOUNT_ISSET_ID);
  }

  /** Returns true if field detailCount is set (has been assigned a value) and false otherwise */
  public boolean isSetDetailCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DETAILCOUNT_ISSET_ID);
  }

  public void setDetailCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DETAILCOUNT_ISSET_ID, value);
  }

  public int getDetailListSize() {
    return (this.detailList == null) ? 0 : this.detailList.size();
  }

  public java.util.Iterator<TAccountDetail> getDetailListIterator() {
    return (this.detailList == null) ? null : this.detailList.iterator();
  }

  public void addToDetailList(TAccountDetail elem) {
    if (this.detailList == null) {
      this.detailList = new java.util.ArrayList<TAccountDetail>();
    }
    this.detailList.add(elem);
  }

  public java.util.List<TAccountDetail> getDetailList() {
    return this.detailList;
  }

  public TDashboardInfo setDetailList(java.util.List<TAccountDetail> detailList) {
    this.detailList = detailList;
    return this;
  }

  public void unsetDetailList() {
    this.detailList = null;
  }

  /** Returns true if field detailList is set (has been assigned a value) and false otherwise */
  public boolean isSetDetailList() {
    return this.detailList != null;
  }

  public void setDetailListIsSet(boolean value) {
    if (!value) {
      this.detailList = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ALIPAY_INFO:
      if (value == null) {
        unsetAlipayInfo();
      } else {
        setAlipayInfo((TAlipay)value);
      }
      break;

    case LAST_DAY_INCOME:
      if (value == null) {
        unsetLastDayIncome();
      } else {
        setLastDayIncome((java.lang.Integer)value);
      }
      break;

    case BALANCE:
      if (value == null) {
        unsetBalance();
      } else {
        setBalance((java.lang.Integer)value);
      }
      break;

    case DETAIL_COUNT:
      if (value == null) {
        unsetDetailCount();
      } else {
        setDetailCount((java.lang.Integer)value);
      }
      break;

    case DETAIL_LIST:
      if (value == null) {
        unsetDetailList();
      } else {
        setDetailList((java.util.List<TAccountDetail>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ALIPAY_INFO:
      return getAlipayInfo();

    case LAST_DAY_INCOME:
      return getLastDayIncome();

    case BALANCE:
      return getBalance();

    case DETAIL_COUNT:
      return getDetailCount();

    case DETAIL_LIST:
      return getDetailList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ALIPAY_INFO:
      return isSetAlipayInfo();
    case LAST_DAY_INCOME:
      return isSetLastDayIncome();
    case BALANCE:
      return isSetBalance();
    case DETAIL_COUNT:
      return isSetDetailCount();
    case DETAIL_LIST:
      return isSetDetailList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TDashboardInfo)
      return this.equals((TDashboardInfo)that);
    return false;
  }

  public boolean equals(TDashboardInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_alipayInfo = true && this.isSetAlipayInfo();
    boolean that_present_alipayInfo = true && that.isSetAlipayInfo();
    if (this_present_alipayInfo || that_present_alipayInfo) {
      if (!(this_present_alipayInfo && that_present_alipayInfo))
        return false;
      if (!this.alipayInfo.equals(that.alipayInfo))
        return false;
    }

    boolean this_present_lastDayIncome = true;
    boolean that_present_lastDayIncome = true;
    if (this_present_lastDayIncome || that_present_lastDayIncome) {
      if (!(this_present_lastDayIncome && that_present_lastDayIncome))
        return false;
      if (this.lastDayIncome != that.lastDayIncome)
        return false;
    }

    boolean this_present_balance = true;
    boolean that_present_balance = true;
    if (this_present_balance || that_present_balance) {
      if (!(this_present_balance && that_present_balance))
        return false;
      if (this.balance != that.balance)
        return false;
    }

    boolean this_present_detailCount = true;
    boolean that_present_detailCount = true;
    if (this_present_detailCount || that_present_detailCount) {
      if (!(this_present_detailCount && that_present_detailCount))
        return false;
      if (this.detailCount != that.detailCount)
        return false;
    }

    boolean this_present_detailList = true && this.isSetDetailList();
    boolean that_present_detailList = true && that.isSetDetailList();
    if (this_present_detailList || that_present_detailList) {
      if (!(this_present_detailList && that_present_detailList))
        return false;
      if (!this.detailList.equals(that.detailList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAlipayInfo()) ? 131071 : 524287);
    if (isSetAlipayInfo())
      hashCode = hashCode * 8191 + alipayInfo.hashCode();

    hashCode = hashCode * 8191 + lastDayIncome;

    hashCode = hashCode * 8191 + balance;

    hashCode = hashCode * 8191 + detailCount;

    hashCode = hashCode * 8191 + ((isSetDetailList()) ? 131071 : 524287);
    if (isSetDetailList())
      hashCode = hashCode * 8191 + detailList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TDashboardInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAlipayInfo()).compareTo(other.isSetAlipayInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAlipayInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.alipayInfo, other.alipayInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLastDayIncome()).compareTo(other.isSetLastDayIncome());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastDayIncome()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastDayIncome, other.lastDayIncome);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetBalance()).compareTo(other.isSetBalance());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBalance()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.balance, other.balance);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDetailCount()).compareTo(other.isSetDetailCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDetailCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.detailCount, other.detailCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDetailList()).compareTo(other.isSetDetailList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDetailList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.detailList, other.detailList);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TDashboardInfo(");
    boolean first = true;

    sb.append("alipayInfo:");
    if (this.alipayInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.alipayInfo);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("lastDayIncome:");
    sb.append(this.lastDayIncome);
    first = false;
    if (!first) sb.append(", ");
    sb.append("balance:");
    sb.append(this.balance);
    first = false;
    if (!first) sb.append(", ");
    sb.append("detailCount:");
    sb.append(this.detailCount);
    first = false;
    if (!first) sb.append(", ");
    sb.append("detailList:");
    if (this.detailList == null) {
      sb.append("null");
    } else {
      sb.append(this.detailList);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (alipayInfo == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'alipayInfo' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'lastDayIncome' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'balance' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'detailCount' because it's a primitive and you chose the non-beans generator.
    if (detailList == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'detailList' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (alipayInfo != null) {
      alipayInfo.validate();
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TDashboardInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TDashboardInfoStandardScheme getScheme() {
      return new TDashboardInfoStandardScheme();
    }
  }

  private static class TDashboardInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<TDashboardInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TDashboardInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ALIPAY_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.alipayInfo = new TAlipay();
              struct.alipayInfo.read(iprot);
              struct.setAlipayInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LAST_DAY_INCOME
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.lastDayIncome = iprot.readI32();
              struct.setLastDayIncomeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BALANCE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.balance = iprot.readI32();
              struct.setBalanceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DETAIL_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.detailCount = iprot.readI32();
              struct.setDetailCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // DETAIL_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.detailList = new java.util.ArrayList<TAccountDetail>(_list0.size);
                TAccountDetail _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new TAccountDetail();
                  _elem1.read(iprot);
                  struct.detailList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setDetailListIsSet(true);
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
      if (!struct.isSetLastDayIncome()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'lastDayIncome' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetBalance()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'balance' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetDetailCount()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'detailCount' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TDashboardInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.alipayInfo != null) {
        oprot.writeFieldBegin(ALIPAY_INFO_FIELD_DESC);
        struct.alipayInfo.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LAST_DAY_INCOME_FIELD_DESC);
      oprot.writeI32(struct.lastDayIncome);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BALANCE_FIELD_DESC);
      oprot.writeI32(struct.balance);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(DETAIL_COUNT_FIELD_DESC);
      oprot.writeI32(struct.detailCount);
      oprot.writeFieldEnd();
      if (struct.detailList != null) {
        oprot.writeFieldBegin(DETAIL_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.detailList.size()));
          for (TAccountDetail _iter3 : struct.detailList)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TDashboardInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TDashboardInfoTupleScheme getScheme() {
      return new TDashboardInfoTupleScheme();
    }
  }

  private static class TDashboardInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<TDashboardInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TDashboardInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.alipayInfo.write(oprot);
      oprot.writeI32(struct.lastDayIncome);
      oprot.writeI32(struct.balance);
      oprot.writeI32(struct.detailCount);
      {
        oprot.writeI32(struct.detailList.size());
        for (TAccountDetail _iter4 : struct.detailList)
        {
          _iter4.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TDashboardInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.alipayInfo = new TAlipay();
      struct.alipayInfo.read(iprot);
      struct.setAlipayInfoIsSet(true);
      struct.lastDayIncome = iprot.readI32();
      struct.setLastDayIncomeIsSet(true);
      struct.balance = iprot.readI32();
      struct.setBalanceIsSet(true);
      struct.detailCount = iprot.readI32();
      struct.setDetailCountIsSet(true);
      {
        org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.detailList = new java.util.ArrayList<TAccountDetail>(_list5.size);
        TAccountDetail _elem6;
        for (int _i7 = 0; _i7 < _list5.size; ++_i7)
        {
          _elem6 = new TAccountDetail();
          _elem6.read(iprot);
          struct.detailList.add(_elem6);
        }
      }
      struct.setDetailListIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
