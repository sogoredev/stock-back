package com.gds.Gestion.de.stock.exceptions;

import com.gds.Gestion.de.stock.exceptions.UtilisateurDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    CONFIG GLOBAL

    @ExceptionHandler(EmailIncorrectException.class)
    public ResponseEntity<String> handleEmailIncorrectException(EmailIncorrectException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MontantQuantiteNullException.class)
    public ResponseEntity<String> handleMontantQuantiteNullException(MontantQuantiteNullException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<String> handleEmptyException(EmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //    ====================================================================

    @ExceptionHandler(UtilisateurNotFoundException.class)
    public ResponseEntity<String> handleUtilisateurNotFoundException(UtilisateurNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UtilisateurDuplicateException.class)
    public ResponseEntity<String> handleUtilisateurDuplicateException(UtilisateurDuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    //    ====================================================================

    @ExceptionHandler(CategorieDuplicateException.class)
    public ResponseEntity<String> handleCategorieDuplicateException(CategorieDuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategorieNotFoundException.class)
    public ResponseEntity<String> handleCategorieNotFoundException(CategorieNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    ====================================================================

    @ExceptionHandler(ProduitDupicateException.class)
    public ResponseEntity<String> handleProduitDupicateException(ProduitDupicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProduitNotFoundException.class)
    public ResponseEntity<String> handleProduitNotFoundException(ProduitNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    ====================================================================

    @ExceptionHandler(ApprovisionDupicateException.class)
    public ResponseEntity<String> handleApprovisionDupicateException(ApprovisionDupicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApprovNotFoundException.class)
    public ResponseEntity<String> handleApprovNotFoundException(ApprovNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    ====================================================================

    @ExceptionHandler(ClientDupicateException.class)
    public ResponseEntity<String> handleClientDupicateException(ClientDupicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    ====================================================================

    @ExceptionHandler(DetteDuplicateException.class)
    public ResponseEntity<String> handleDetteDuplicateException(DetteDuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DetteNotFoundException.class)
    public ResponseEntity<String> handleDetteNotFoundException(DetteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    ====================================================================

    @ExceptionHandler(VenteDupicateException.class)
    public ResponseEntity<String> handleVenteDupicateException(VenteDupicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VenteNotFoundException.class)
    public ResponseEntity<String> handleVenteNotFoundException(VenteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
