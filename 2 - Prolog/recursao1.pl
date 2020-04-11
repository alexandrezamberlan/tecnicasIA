escrever(N) :- N == 0, !. %poda
escrever(N) :- writeln(N),
               Resp is N - 1,
               escrever(Resp).
