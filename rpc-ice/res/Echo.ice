module Service {

["java:serializable:edu.ucsb.cs.rpc.base.DataObject"]
sequence<byte> DataObject;

sequence<int> ia;
sequence<byte> ba;
dictionary<int, int> intMap;

	interface Echo {
		void doNothing();
	    string echoString(string s);
	    int echoInt(int i);
	    ia echoArray(ia array);
	    ba echoBlob(ba blob);
	    DataObject echoObject(DataObject obj);
	    intMap echoMap(intMap map);
	};
};