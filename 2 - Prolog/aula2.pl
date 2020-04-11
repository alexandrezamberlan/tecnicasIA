%--alunos matriculados
% aluno( Num, Nome ) :- o aluno Nome esta matriculado com o codigo Num.
aluno( 1, pedro ).
aluno( 2, ana ).
aluno( 3, rui ).
aluno( 4, to ).
aluno( 5, ze ).
aluno( 6, marta ).
aluno( 7, 'filipa alexandra' ).
aluno( 8, bento ).
aluno( 9, zara ).

%--cursos registados na UM: 400=EEng; 200=ECiencias
% curso( Cod, Sigla ) :- Cod � o c�digo interno da licenciatura designada pela Sigla.
curso( 400001,'ECom' ).
curso( 400002,eSI ).
curso( 400003,'IG' ).
curso( 400004,eBM ).
curso( 200001,'MCC' ).

%--alunos inscritos nos cursos
% insc( Num, Cod ) :- o aluno de n�mero Num est� inscrito no curso de c�digo Cod.
insc( 1, 400001 ).
insc( 2, 400001 ).
insc( 3, 400001 ).
insc( 4, 400002 ).
insc( 5, 400002 ).
insc( 6, 400003 ).
insc( 7, 400004 ).
insc( 8, 200001 ).
insc( 9, 200001 ).

% inscrito( Aluno, Curso ) :- o Aluno descrito pelo seu numero e nome est� inscrito no Curso definido pelo codigo e sigla.
inscrito( al(Num,Nome), cur(Cod,Sigla) ) :- aluno( Num,Nome ),
                                            curso( Cod,Sigla ),
                                            insc( Num,Cod ).
                    
