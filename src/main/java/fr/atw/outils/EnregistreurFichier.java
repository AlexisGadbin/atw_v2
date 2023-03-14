package fr.atw.outils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class EnregistreurFichier {
	private Part part;
	private String nomFichier;
	private String path;
	private static final int TAILLE_TAMPON = 10240;
	
	public EnregistreurFichier(Part fichier, String path) {
        this.part = fichier;
        this.path = path;
        this.nomFichier = getNomFichier(part);
        
	}

	
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }  
    
    private void verifierFichier() throws IOException {
        if (this.nomFichier != null && !this.nomFichier.isEmpty()) {
             this.nomFichier = this.nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(this.nomFichier.lastIndexOf('\\') + 1);
        }
    }
    
    public void ecrireFichier() throws IOException {
    	this.verifierFichier();

    	
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(path + "\\" + this.nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }


	public String getNomFichier() {
		return nomFichier;
	}
    
    
}
