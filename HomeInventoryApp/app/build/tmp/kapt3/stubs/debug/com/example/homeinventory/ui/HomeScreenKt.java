package com.example.homeinventory.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a8\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00a8\u0006\u0010"}, d2 = {"CategoryItem", "", "name", "", "onClick", "Lkotlin/Function0;", "HomeScreen", "navController", "Landroidx/navigation/NavController;", "onAddItemClick", "onRoomClick", "viewModel", "Lcom/example/homeinventory/viewmodel/SharedInventoryViewModel;", "ItemRow", "item", "Lcom/example/homeinventory/model/Item;", "app_debug"})
@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
public final class HomeScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddItemClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onRoomClick, @org.jetbrains.annotations.NotNull
    com.example.homeinventory.viewmodel.SharedInventoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CategoryItem(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ItemRow(@org.jetbrains.annotations.NotNull
    com.example.homeinventory.model.Item item) {
    }
}