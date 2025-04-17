package com.example.homeinventory.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0081\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u000326\u0010\t\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0010H\u0007\u00a2\u0006\u0002\u0010\u0011\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0007\u00a8\u0006\u0016"}, d2 = {"GridLayout", "", "rows", "", "cols", "placedItems", "", "Lcom/example/homeinventory/model/Item;", "selectedItemId", "onCellClicked", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "x", "y", "onItemClicked", "Lkotlin/Function1;", "(IILjava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "RoomLayoutScreen", "roomId", "viewModel", "Lcom/example/homeinventory/viewmodel/SharedInventoryViewModel;", "app_debug"})
@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
public final class RoomLayoutScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void RoomLayoutScreen(int roomId, @org.jetbrains.annotations.NotNull
    com.example.homeinventory.viewmodel.SharedInventoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void GridLayout(int rows, int cols, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.homeinventory.model.Item> placedItems, @org.jetbrains.annotations.Nullable
    java.lang.Integer selectedItemId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> onCellClicked, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.homeinventory.model.Item, kotlin.Unit> onItemClicked) {
    }
}