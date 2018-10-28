package com.electricsheeps.myreception.presentation.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public final class ArbitraryCellSelector {
    private List<Cell> cellList = new ArrayList<>();

    public void addCell(Cell cell) {
        cellList.add(cell);
    }

    public void removeCell(Cell cell) {
        cellList.remove(cell);
    }

    public Cell getCell(Object item) {
        for (Cell cell : cellList) {
            if (cell.is(item)) {
                return cell;
            }
        }
        throw new NoSuchRecyclerRowException();
    }

    public Cell getCell(int viewType) {
        for (Cell cell : cellList) {
            if (cell.type() == viewType) {
                return cell;
            }
        }
        throw new NoSuchRecyclerRowException();
    }

    public interface Cell {

        boolean is(Object item);

        int type();

        RecyclerView.ViewHolder holder(ViewGroup parent);

        void bind(RecyclerView.ViewHolder holder, Object item);
    }
}
