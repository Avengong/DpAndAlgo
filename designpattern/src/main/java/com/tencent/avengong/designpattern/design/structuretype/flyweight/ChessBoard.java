package com.tencent.avengong.designpattern.design.structuretype.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    Map<Integer, ChessPiece> pieceMap = new HashMap<>();

    public void init() {
        pieceMap.put(1, new ChessPiece(ChessPieceUnitFactory.getPieceUnit(1), 100, 100));


    }

    public void move(int id, int posX, int posy) {

    }

}
