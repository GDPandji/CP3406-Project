package com.example.homeinventory.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J8\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\r2\b\u0010>\u001a\u0004\u0018\u00010\rJ\u000e\u0010?\u001a\u0002082\u0006\u00109\u001a\u00020\rJ\b\u0010@\u001a\u000208H\u0002J\u0006\u0010A\u001a\u000208J\u0016\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0C2\u0006\u0010D\u001a\u00020\u0013J\u0006\u0010E\u001a\u000208J\u000e\u0010F\u001a\u0002082\u0006\u0010G\u001a\u00020\bJ\u000e\u0010H\u001a\u0002082\u0006\u0010I\u001a\u00020\rJ\u000e\u0010J\u001a\u0002082\u0006\u0010K\u001a\u00020\rJ\u000e\u0010L\u001a\u0002082\u0006\u0010M\u001a\u00020\rJ\u0010\u0010N\u001a\u0002082\b\u0010O\u001a\u0004\u0018\u00010\rJ\u000e\u0010P\u001a\u0002082\u0006\u0010Q\u001a\u00020\rJ\u000e\u0010R\u001a\u0002082\u0006\u0010S\u001a\u00020\u0013J\u0016\u0010T\u001a\u0002082\u0006\u0010;\u001a\u00020\u00132\u0006\u0010M\u001a\u00020\rJ\u000e\u0010T\u001a\u0002082\u0006\u0010M\u001a\u00020\rR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0019\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001aR\u000e\u0010/\u001a\u000200X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001aR\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001aR\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001a\u00a8\u0006U"}, d2 = {"Lcom/example/homeinventory/viewmodel/SharedInventoryViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_allItems", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/homeinventory/model/Item;", "_allRooms", "Lcom/example/homeinventory/model/RoomEntity;", "_itemCategory", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_itemDescription", "_itemIcon", "_itemImageUri", "_itemName", "_itemQuantity", "", "_itemRoomId", "_roomIcon", "_roomsWithItems", "Lcom/example/homeinventory/model/RoomWithItems;", "allItems", "getAllItems", "()Lkotlinx/coroutines/flow/StateFlow;", "allRooms", "getAllRooms", "db", "Lcom/example/homeinventory/data/AppDatabase;", "itemCategory", "getItemCategory", "itemDao", "Lcom/example/homeinventory/model/ItemDao;", "itemDescription", "getItemDescription", "itemIcon", "getItemIcon", "itemImageUri", "getItemImageUri", "itemName", "getItemName", "itemQuantity", "getItemQuantity", "itemRoomId", "getItemRoomId", "roomDao", "Lcom/example/homeinventory/model/RoomDao;", "roomIcon", "getRoomIcon", "roomsWithItems", "getRoomsWithItems", "uniqueCategories", "getUniqueCategories", "addItem", "", "name", "description", "roomId", "quantity", "category", "imageUri", "addRoom", "clearItemFields", "decreaseQuantity", "getItemById", "Lkotlinx/coroutines/flow/Flow;", "id", "increaseQuantity", "updateItem", "updatedItem", "updateItemCategory", "newCategory", "updateItemDescription", "newDescription", "updateItemIcon", "newIcon", "updateItemImageUri", "newUri", "updateItemName", "newName", "updateItemRoomId", "newRoomId", "updateRoomIcon", "app_debug"})
public final class SharedInventoryViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.homeinventory.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.homeinventory.model.ItemDao itemDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.homeinventory.model.RoomDao roomDao = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.Item>> _allItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.Item>> allItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> uniqueCategories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.RoomEntity>> _allRooms = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.RoomEntity>> allRooms = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.RoomWithItems>> _roomsWithItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.RoomWithItems>> roomsWithItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _itemName = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> itemName = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _itemDescription = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> itemDescription = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _itemRoomId = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> itemRoomId = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _itemQuantity = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> itemQuantity = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _itemCategory = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> itemCategory = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _itemImageUri = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> itemImageUri = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _itemIcon = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> itemIcon = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _roomIcon = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> roomIcon = null;
    
    public SharedInventoryViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.Item>> getAllItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getUniqueCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.RoomEntity>> getAllRooms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.homeinventory.model.RoomWithItems>> getRoomsWithItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getItemName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getItemDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getItemRoomId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getItemQuantity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getItemCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getItemImageUri() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getItemIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getRoomIcon() {
        return null;
    }
    
    public final void updateItemName(@org.jetbrains.annotations.NotNull
    java.lang.String newName) {
    }
    
    public final void updateItemDescription(@org.jetbrains.annotations.NotNull
    java.lang.String newDescription) {
    }
    
    public final void updateItemRoomId(int newRoomId) {
    }
    
    public final void updateItemCategory(@org.jetbrains.annotations.NotNull
    java.lang.String newCategory) {
    }
    
    public final void updateItemImageUri(@org.jetbrains.annotations.Nullable
    java.lang.String newUri) {
    }
    
    public final void updateItemIcon(@org.jetbrains.annotations.NotNull
    java.lang.String newIcon) {
    }
    
    public final void increaseQuantity() {
    }
    
    public final void decreaseQuantity() {
    }
    
    public final void updateRoomIcon(@org.jetbrains.annotations.NotNull
    java.lang.String newIcon) {
    }
    
    public final void addItem(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String description, int roomId, int quantity, @org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.Nullable
    java.lang.String imageUri) {
    }
    
    public final void updateItem(@org.jetbrains.annotations.NotNull
    com.example.homeinventory.model.Item updatedItem) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.example.homeinventory.model.Item> getItemById(int id) {
        return null;
    }
    
    public final void addRoom(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
    }
    
    public final void updateRoomIcon(int roomId, @org.jetbrains.annotations.NotNull
    java.lang.String newIcon) {
    }
    
    private final void clearItemFields() {
    }
}