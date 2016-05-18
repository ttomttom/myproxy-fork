package edu.uiuc.ncsa.myproxy.oa4mp.client.storage;

import edu.uiuc.ncsa.security.storage.sql.internals.ColumnDescriptorEntry;
import edu.uiuc.ncsa.security.storage.sql.internals.Table;

import java.sql.Types;

import static java.sql.Types.LONGVARCHAR;

/**
 * Internal model of the SQL table backing an {@link AssetStore}.
 * <p>Created by Jeff Gaynor<br>
 * on 1/31/13 at  11:54 AM
 */
public class AssetStoreTable extends Table {
    public static final String DEFAULT_TABLENAME = "assets";

    public AssetStoreTable(AssetSerializationKeys keys, String schema, String tablenamePrefix, String tablename) {
        super(keys, schema, tablenamePrefix, tablename);
    }

    AssetSerializationKeys ask(){
        return (AssetSerializationKeys) keys;
    }
    @Override
      public void createColumnDescriptors() {
          super.createColumnDescriptors();
          
          //BUG: Don't set the username to be primary key, because it is not getting UPDATEed 
          //getColumnDescriptor().add(new ColumnDescriptorEntry(ask().username(), LONGVARCHAR, false, true));
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().username(), LONGVARCHAR, true, false));
          
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().certificates(), LONGVARCHAR, true, false));
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().privateKey(), LONGVARCHAR, true, false));
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().redirect(), LONGVARCHAR, true, false));
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().creationTime(), Types.TIMESTAMP, true, false));
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().certReq(), LONGVARCHAR, true, false));
          getColumnDescriptor().add(new ColumnDescriptorEntry(ask().token(), LONGVARCHAR, true, false));

      }
    public String getByTokenStatement(){
        return "select * from " + getFQTablename() + " where " + ask().token() + "=?";
    }
}
