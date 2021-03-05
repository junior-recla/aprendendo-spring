package com.example.demo.model.domain;

import lombok.*;

//@Data//A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, and @Setter on all non-final fields, and @RequiredArgsConstructor!
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(exclude="id")
//@EqualsAndHashCode(exclude={"nome", "quantidade", "valor"})
//@Builder
@Value//is the immutable variant of @Data; all fields are made private and final by default, and setters are not generated.
public class Produto {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private Double valor;
}


/*
* @Slf4j
* @Log
* @CommonsLog
* // log.debug(), log.info(), ...
* */
//https://medium.com/collabcode/projeto-lombok-escrevendo-menos-c%C3%B3digo-em-java-8fc87b379209
