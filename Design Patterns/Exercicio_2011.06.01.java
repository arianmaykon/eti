public class Visualizador {

    public void Visualizar() {
        Imagem img = ImagemFactory.getImagem(ImagemFactory.BMP, "/home/nome/viagem.bmp"));
        img.carregar();
        img.exibir();
        img.fechar();
    }
}

public class ImagemFactory {

    static final int BMP = 1;
    static final int JPG = 2;
    static final int PNG = 3;
    static final int TIFF = 4;


    public void static getImagem(int tipo, String path) {
        switch(tipo) {
            case ImagemFactory.BMP:
                return new ImagemBMP(path);
            case ImagemFactory.JPG:
                return new ImagemJPG(path);
            case ImagemFactory.PNG:
                return new ImagemPNG(path);
            case ImagemFactory.TIFF:
                return new ImagemTIFF(path);
        }
    }
}

abstract public class Imagem {

    protected String tipo = "Imagem";
    protected String path;

    public Imagem(String path) {
        this.path = path;
    }

    public void carregar() {
        System.out.println("Imagem " + this.tipo);
        System.out.println("Carregando a imagem " + this.tipo);
    }

    public void exibir() {
        System.out.println("Exibindo a imagem " + this.tipo);
    }

    public void fechar() {
        System.out.println("Fechando a imagem " + this.tipo");
    }
}

public class ImagemBMP extends Imagem {

    protected String tipo = "BMP";
}


public class ImagemJPG extends Imagem {

    protected String tipo = "JPG";
}


public class ImagemPNG extends Imagem {

    protected String tipo = "PNG";
}


public class ImagemTIFF extends Imagem {

    protected String tipo = "TIFF";
}
