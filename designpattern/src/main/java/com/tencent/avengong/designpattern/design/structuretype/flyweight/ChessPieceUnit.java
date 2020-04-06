package com.tencent.avengong.designpattern.design.structuretype.flyweight;

public class ChessPieceUnit {

    private long id;
    private String text;
    private PieceColor pieceColor;


    public ChessPieceUnit(long id, String text, PieceColor pieceColor) {
        this.id = id;
        this.text = text;
        this.pieceColor = pieceColor;
    }

    enum PieceColor {
        BLACK, RED
    }

}
