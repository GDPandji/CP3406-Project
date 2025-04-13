package com.example.homeinventory.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a<\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a$\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u00a8\u0006\u0010"}, d2 = {"AddRoomCard", "", "onClick", "Lkotlin/Function0;", "RoomCard", "roomName", "", "itemCount", "", "emoji", "onEditClick", "RoomScreen", "viewModel", "Lcom/example/homeinventory/viewmodel/SharedInventoryViewModel;", "onRoomSelected", "Lkotlin/Function1;", "app_debug"})
@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
public final class RoomScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void RoomScreen(@org.jetbrains.annotations.NotNull
    com.example.homeinventory.viewmodel.SharedInventoryViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onRoomSelected) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void RoomCard(@org.jetbrains.annotations.NotNull
    java.lang.String roomName, int itemCount, @org.jetbrains.annotations.NotNull
    java.lang.String emoji, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onEditClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AddRoomCard(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}