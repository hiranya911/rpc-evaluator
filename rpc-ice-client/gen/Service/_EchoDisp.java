// **********************************************************************
//
// Copyright (c) 2003-2011 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.4.2
//
// <auto-generated>
//
// Generated from file `_EchoDisp.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Service;

public abstract class _EchoDisp extends Ice.ObjectImpl implements Echo
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::Service::Echo"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[1];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String
    ice_staticId()
    {
        return __ids[1];
    }

    public final void
    doNothing()
    {
        doNothing(null);
    }

    public final int[]
    echoArray(int[] array)
    {
        return echoArray(array, null);
    }

    public final byte[]
    echoBlob(byte[] blob)
    {
        return echoBlob(blob, null);
    }

    public final int
    echoInt(int i)
    {
        return echoInt(i, null);
    }

    public final java.util.Map<java.lang.Integer, java.lang.Integer>
    echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map)
    {
        return echoMap(map, null);
    }

    public final edu.ucsb.cs.rpc.base.DataObject
    echoObject(edu.ucsb.cs.rpc.base.DataObject obj)
    {
        return echoObject(obj, null);
    }

    public final String
    echoString(String s)
    {
        return echoString(s, null);
    }

    public static Ice.DispatchStatus
    ___doNothing(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.is().skipEmptyEncaps();
        __obj.doNothing(__current);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___echoString(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        String s;
        s = __is.readString();
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        String __ret = __obj.echoString(s, __current);
        __os.writeString(__ret);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___echoInt(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        int i;
        i = __is.readInt();
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        int __ret = __obj.echoInt(i, __current);
        __os.writeInt(__ret);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___echoArray(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        int[] array;
        array = iaHelper.read(__is);
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        int[] __ret = __obj.echoArray(array, __current);
        iaHelper.write(__os, __ret);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___echoBlob(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        byte[] blob;
        blob = baHelper.read(__is);
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        byte[] __ret = __obj.echoBlob(blob, __current);
        baHelper.write(__os, __ret);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___echoObject(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        edu.ucsb.cs.rpc.base.DataObject obj;
        obj = (edu.ucsb.cs.rpc.base.DataObject)__is.readSerializable();
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        edu.ucsb.cs.rpc.base.DataObject __ret = __obj.echoObject(obj, __current);
        __os.writeSerializable(__ret);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___echoMap(Echo __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        java.util.Map<java.lang.Integer, java.lang.Integer> map;
        map = intMapHelper.read(__is);
        __is.endReadEncaps();
        IceInternal.BasicStream __os = __inS.os();
        java.util.Map<java.lang.Integer, java.lang.Integer> __ret = __obj.echoMap(map, __current);
        intMapHelper.write(__os, __ret);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "doNothing",
        "echoArray",
        "echoBlob",
        "echoInt",
        "echoMap",
        "echoObject",
        "echoString",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public Ice.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___doNothing(this, in, __current);
            }
            case 1:
            {
                return ___echoArray(this, in, __current);
            }
            case 2:
            {
                return ___echoBlob(this, in, __current);
            }
            case 3:
            {
                return ___echoInt(this, in, __current);
            }
            case 4:
            {
                return ___echoMap(this, in, __current);
            }
            case 5:
            {
                return ___echoObject(this, in, __current);
            }
            case 6:
            {
                return ___echoString(this, in, __current);
            }
            case 7:
            {
                return ___ice_id(this, in, __current);
            }
            case 8:
            {
                return ___ice_ids(this, in, __current);
            }
            case 9:
            {
                return ___ice_isA(this, in, __current);
            }
            case 10:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeTypeId(ice_staticId());
        __os.startWriteSlice();
        __os.endWriteSlice();
        super.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is, boolean __rid)
    {
        if(__rid)
        {
            __is.readTypeId();
        }
        __is.startReadSlice();
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type Service::Echo was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type Service::Echo was not generated with stream support";
        throw ex;
    }
}
