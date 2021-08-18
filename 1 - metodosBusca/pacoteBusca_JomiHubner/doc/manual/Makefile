#
# by Jomi
#

TARGET = $(shell ls *.tex | cut -f 1 -d ".")

TEX = $(shell ls *.tex)
STY = $(shell ls *.sty 2> /dev/null)
BST = $(shell ls *.bst 2> /dev/null)
CLS = $(shell ls *.cls 2> /dev/null)

#figuras
FIG = $(shell find . -name *.fig)
DIA = $(shell find . -name *.dia)
EPS = $(FIG:.fig=.eps) $(DIA:.dia=.eps)
PDF = $(FIG:.fig=.pdf) $(DIA:.dia=.pdf)

PUBLIC_HTML = /Volumes/jomi/public_html/ia/exercicios

all: $(TARGET).dvi

$(TARGET).dvi: $(TEX) $(BST) $(STY) $(CLS) $(EPS)  $(TARGET).bbl 
	latex $(TARGET)

	@while ( grep Rerun $(TARGET).log > /dev/null ); do \
		echo "denovo latex (rerun)"; \
		latex $(TARGET) > /dev/null; \
	done


%.eps: %.fig
	@echo fig2dev $(@:.eps=.fig)
	@fig2dev -L eps $(@:.eps=.fig) $@

%.eps: %.dia
	@echo dia $(@:.eps=.dia)
	@dia -e $@ $(@:.eps=.dia) 2> /dev/null

%.pdf: %.eps
	epstopdf $(@:.pdf=.eps) > $@


$(TARGET).pdf: $(TARGET).dvi
	dvipdfm -p a4 $(TARGET)

html: $(TEX)
	latex2html -dir html -mkdir -split 0 -white -image_type=png -nonavigation -noinfo -style http://www.inf.furb.br/%7Ejomi/labs.css $(TARGET)

$(TARGET).ps:
	rm $(TARGET).dvi || true
	touch .ps
	make $(TARGET).dvi
	dvips -t a4 $(TARGET) -o
	rm .ps || true

$(TARGET).ps.gz: $(TARGET).ps
	gzip -f $(TARGET).ps

$(TARGET).aux:
	latex $(TARGET)

$(TARGET).bbl:
	make bib

bib: $(TARGET).aux
	@rm .btmp 2> /dev/null || true
	@if ( grep "\\\\bibliography{" $(TARGET).tex > /dev/null ); then \
		bibtex $(TARGET); \
		grep "^\\\\citation" $(TARGET).aux > .btmp; \
	fi


index:
	@if [ -f $(TARGET).idx ]; then \
		makeindex $(TARGET); \
		cp $(TARGET).idx .itmp; \
	fi

view: $(TARGET).dvi
	xdvi $(TARGET)

publicaPDF: $(TARGET).pdf
	cp $(TARGET).pdf $(PUBLIC_HTML)

publicaHTML:
	cp -R html/$(TARGET).html $(PUBLIC_HTML)

final: $(TARGET).dvi
	make bib
	make index
	latex $(TARGET) > /dev/null
	make $(TARGET).ps.gz
	make $(TARGET).pdf

clean:
	rm -f $(TARGET).ps *.lo? *.toc *.blg $(TARGET).aux $(TARGET).out core $(TARGET).bbl $(TARGET).idx $(TARGET).ilg $(TARGET).ind .itmp .btmp || true
	rm -rf auto || true
	rm -rf html || true
	find . -name *bak -exec rm {} \; || true
	find . -name *~ -exec rm {} \; || true

cleanpdf:
	find . -name "*.pdf" -exec rm {} \;

cleanps:
	find . -name "*.ps" -exec rm {} \;



