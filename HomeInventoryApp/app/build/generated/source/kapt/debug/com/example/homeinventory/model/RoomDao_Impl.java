package com.example.homeinventory.model;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RoomDao_Impl implements RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RoomEntity> __insertionAdapterOfRoomEntity;

  private final EntityDeletionOrUpdateAdapter<RoomEntity> __updateAdapterOfRoomEntity;

  public RoomDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoomEntity = new EntityInsertionAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `RoomEntity` (`id`,`name`,`icon`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getIcon() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getIcon());
        }
      }
    };
    this.__updateAdapterOfRoomEntity = new EntityDeletionOrUpdateAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `RoomEntity` SET `id` = ?,`name` = ?,`icon` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getIcon() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getIcon());
        }
        statement.bindLong(4, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final RoomEntity room, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomEntity.insert(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final RoomEntity room, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRoomEntity.handle(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<RoomEntity>> getAllRooms() {
    final String _sql = "SELECT * FROM RoomEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"RoomEntity"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpIcon;
            if (_cursor.isNull(_cursorIndexOfIcon)) {
              _tmpIcon = null;
            } else {
              _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            }
            _item = new RoomEntity(_tmpId,_tmpName,_tmpIcon);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<RoomWithItems>> getRoomsWithItems() {
    final String _sql = "SELECT * FROM RoomEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"items",
        "RoomEntity"}, new Callable<List<RoomWithItems>>() {
      @Override
      @NonNull
      public List<RoomWithItems> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
            final LongSparseArray<ArrayList<Item>> _collectionItems = new LongSparseArray<ArrayList<Item>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionItems.containsKey(_tmpKey)) {
                _collectionItems.put(_tmpKey, new ArrayList<Item>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipitemsAscomExampleHomeinventoryModelItem(_collectionItems);
            final List<RoomWithItems> _result = new ArrayList<RoomWithItems>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final RoomWithItems _item;
              final RoomEntity _tmpRoom;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpIcon;
              if (_cursor.isNull(_cursorIndexOfIcon)) {
                _tmpIcon = null;
              } else {
                _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
              }
              _tmpRoom = new RoomEntity(_tmpId,_tmpName,_tmpIcon);
              final ArrayList<Item> _tmpItemsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpItemsCollection = _collectionItems.get(_tmpKey_1);
              _item = new RoomWithItems(_tmpRoom,_tmpItemsCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipitemsAscomExampleHomeinventoryModelItem(
      @NonNull final LongSparseArray<ArrayList<Item>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipitemsAscomExampleHomeinventoryModelItem(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name`,`description`,`roomId`,`quantity`,`category`,`imageUri`,`icon` FROM `items` WHERE `roomId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "roomId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfDescription = 2;
      final int _cursorIndexOfRoomId = 3;
      final int _cursorIndexOfQuantity = 4;
      final int _cursorIndexOfCategory = 5;
      final int _cursorIndexOfImageUri = 6;
      final int _cursorIndexOfIcon = 7;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<Item> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final Item _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpDescription;
          if (_cursor.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
          }
          final int _tmpRoomId;
          _tmpRoomId = _cursor.getInt(_cursorIndexOfRoomId);
          final int _tmpQuantity;
          _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
          final String _tmpCategory;
          if (_cursor.isNull(_cursorIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
          }
          final String _tmpImageUri;
          if (_cursor.isNull(_cursorIndexOfImageUri)) {
            _tmpImageUri = null;
          } else {
            _tmpImageUri = _cursor.getString(_cursorIndexOfImageUri);
          }
          final String _tmpIcon;
          if (_cursor.isNull(_cursorIndexOfIcon)) {
            _tmpIcon = null;
          } else {
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
          }
          _item_1 = new Item(_tmpId,_tmpName,_tmpDescription,_tmpRoomId,_tmpQuantity,_tmpCategory,_tmpImageUri,_tmpIcon);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
