package com.tencent.avengong.designpattern.design.structuretype.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ChessPieceUnitFactory {

    static Map<Integer, ChessPieceUnit> pieceMap = new HashMap<>();

    static {
        pieceMap.put(1, new ChessPieceUnit(1, "车", ChessPieceUnit.PieceColor.BLACK));
        //....
    }

    public static ChessPieceUnit getPieceUnit(int id) {
        return pieceMap.get(id);

    }

}
