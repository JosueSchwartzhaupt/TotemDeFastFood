package com.josuesch.model;

import com.josuesch.model.enums.Sabor;
import com.josuesch.model.enums.Tamanho;
import com.josuesch.model.item.Bebida;
import com.josuesch.model.item.Lanche;
import com.josuesch.model.item.Sobremesa;

import java.util.Map;

// Coleção de todos os Itens do restaurante em questão
public class Cardapio {
    public static final Lanche X_BURGER =
            new Lanche(
                    "X-Burger",
                    18.0,
                    "Pão, carne e queijo"
            );

    public static final Lanche X_SALADA =
            new Lanche(
                    "X-Salada",
                    22.0,
                    "Pão, carne, queijo, alface e tomate"
            );

    public static final Bebida REFRI_PEQUENO =
            new Bebida("Refrigerante", 6.0, Tamanho.PEQUENO);

    public static final Bebida REFRI_MEDIO =
            new Bebida("Refrigerante", 8.0, Tamanho.MEDIO);

    public static final Bebida REFRI_GRANDE =
            new Bebida("Refrigerante", 10.0, Tamanho.GRANDE);

    public static final Bebida SUCO_PEQUENO =
            new Bebida("Suco", 7.00, Tamanho.PEQUENO);

    public static final Bebida SUCO_MEDIO =
            new Bebida("Suco", 9.00, Tamanho.MEDIO);

    public static final Bebida SUCO_GRANDE =
            new Bebida("Suco", 11.00, Tamanho.GRANDE);

    public static final Sobremesa CASQUINHA_CHOCOLATE =
            new Sobremesa("Casquinha", 5.00, Sabor.CHOCOLATE);

    public static final Sobremesa CASQUINHA_BAUNILHA =
            new Sobremesa("Casquinha", 5.00, Sabor.BAUNILHA);

    public static final Sobremesa CASQUINHA_MISTO =
            new Sobremesa("Casquinha", 5.00, Sabor.MISTO);

    // Sundaes
    public static final Sobremesa SUNDAE_CHOCOLATE =
            new Sobremesa("Sundae", 8.00, Sabor.CHOCOLATE);

    public static final Sobremesa SUNDAE_BAUNILHA =
            new Sobremesa("Sundae", 8.00, Sabor.BAUNILHA);

    public static final Sobremesa SUNDAE_MISTO =
            new Sobremesa("Sundae", 8.00, Sabor.MISTO);

    public static final Map<Tamanho, Bebida> REFRIGERANTES =
            Map.of(
                    Tamanho.PEQUENO, REFRI_PEQUENO,
                    Tamanho.MEDIO, REFRI_MEDIO,
                    Tamanho.GRANDE, REFRI_GRANDE
            );

    public static final Map<Tamanho, Bebida> SUCOS =
            Map.of(
                    Tamanho.PEQUENO, SUCO_PEQUENO,
                    Tamanho.MEDIO, SUCO_MEDIO,
                    Tamanho.GRANDE, SUCO_GRANDE
            );

    public static final Map<Sabor, Sobremesa> CASQUINHAS =
            Map.of(
                    Sabor.CHOCOLATE, CASQUINHA_CHOCOLATE,
                    Sabor.BAUNILHA, CASQUINHA_BAUNILHA,
                    Sabor.MISTO, CASQUINHA_MISTO
            );

    public static final Map<Sabor, Sobremesa> SUNDAES =
            Map.of(
                    Sabor.CHOCOLATE, SUNDAE_CHOCOLATE,
                    Sabor.BAUNILHA, SUNDAE_BAUNILHA,
                    Sabor.MISTO, SUNDAE_MISTO
            );
}
