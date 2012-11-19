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
// Generated from file `EchoPrx.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package edu.ucsb.cs.rpc.ice.service;

public interface EchoPrx extends Ice.ObjectPrx
{
    public void doNothing();

    public void doNothing(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_doNothing();

    public Ice.AsyncResult begin_doNothing(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_doNothing(Ice.Callback __cb);

    public Ice.AsyncResult begin_doNothing(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_doNothing(Callback_Echo_doNothing __cb);

    public Ice.AsyncResult begin_doNothing(java.util.Map<String, String> __ctx, Callback_Echo_doNothing __cb);

    public void end_doNothing(Ice.AsyncResult __result);

    public String echoString(String s);

    public String echoString(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoString(String s);

    public Ice.AsyncResult begin_echoString(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoString(String s, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoString(String s, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoString(String s, Callback_Echo_echoString __cb);

    public Ice.AsyncResult begin_echoString(String s, java.util.Map<String, String> __ctx, Callback_Echo_echoString __cb);

    public String end_echoString(Ice.AsyncResult __result);

    public int echoInt(int i);

    public int echoInt(int i, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoInt(int i);

    public Ice.AsyncResult begin_echoInt(int i, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoInt(int i, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoInt(int i, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoInt(int i, Callback_Echo_echoInt __cb);

    public Ice.AsyncResult begin_echoInt(int i, java.util.Map<String, String> __ctx, Callback_Echo_echoInt __cb);

    public int end_echoInt(Ice.AsyncResult __result);

    public int[] echoArray(int[] array);

    public int[] echoArray(int[] array, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoArray(int[] array);

    public Ice.AsyncResult begin_echoArray(int[] array, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoArray(int[] array, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoArray(int[] array, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoArray(int[] array, Callback_Echo_echoArray __cb);

    public Ice.AsyncResult begin_echoArray(int[] array, java.util.Map<String, String> __ctx, Callback_Echo_echoArray __cb);

    public int[] end_echoArray(Ice.AsyncResult __result);

    public byte[] echoBlob(byte[] blob);

    public byte[] echoBlob(byte[] blob, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoBlob(byte[] blob);

    public Ice.AsyncResult begin_echoBlob(byte[] blob, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoBlob(byte[] blob, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoBlob(byte[] blob, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoBlob(byte[] blob, Callback_Echo_echoBlob __cb);

    public Ice.AsyncResult begin_echoBlob(byte[] blob, java.util.Map<String, String> __ctx, Callback_Echo_echoBlob __cb);

    public byte[] end_echoBlob(Ice.AsyncResult __result);

    public edu.ucsb.cs.rpc.base.DataObject echoObject(edu.ucsb.cs.rpc.base.DataObject obj);

    public edu.ucsb.cs.rpc.base.DataObject echoObject(edu.ucsb.cs.rpc.base.DataObject obj, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoObject(edu.ucsb.cs.rpc.base.DataObject obj);

    public Ice.AsyncResult begin_echoObject(edu.ucsb.cs.rpc.base.DataObject obj, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoObject(edu.ucsb.cs.rpc.base.DataObject obj, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoObject(edu.ucsb.cs.rpc.base.DataObject obj, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoObject(edu.ucsb.cs.rpc.base.DataObject obj, Callback_Echo_echoObject __cb);

    public Ice.AsyncResult begin_echoObject(edu.ucsb.cs.rpc.base.DataObject obj, java.util.Map<String, String> __ctx, Callback_Echo_echoObject __cb);

    public edu.ucsb.cs.rpc.base.DataObject end_echoObject(Ice.AsyncResult __result);

    public java.util.Map<java.lang.Integer, java.lang.Integer> echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map);

    public java.util.Map<java.lang.Integer, java.lang.Integer> echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map);

    public Ice.AsyncResult begin_echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map, Callback_Echo_echoMap __cb);

    public Ice.AsyncResult begin_echoMap(java.util.Map<java.lang.Integer, java.lang.Integer> map, java.util.Map<String, String> __ctx, Callback_Echo_echoMap __cb);

    public java.util.Map<java.lang.Integer, java.lang.Integer> end_echoMap(Ice.AsyncResult __result);
}
