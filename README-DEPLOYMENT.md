# Deployment Guide pentru Nearby Backend

## Deployment pe Render.com

### Configurație actualizată ✅
- ✅ Aplicația folosește PostgreSQL database (persistent)
- ✅ Portul este configurat să folosească variabila PORT de la Render
- ✅ Configurația este pregătită pentru producție

### Configurație Database

#### PostgreSQL pe Render.com
Aplicația este configurată să folosească PostgreSQL cu următoarele variabile de mediu:
- `DATABASE_URL`: URL-ul de conexiune la PostgreSQL
- `DB_USERNAME`: username-ul bazei de date
- `DB_PASSWORD`: parola bazei de date

#### Configurație locală vs producție
- **Local (Development)**: Folosește H2 database în memorie
- **Producție**: Folosește PostgreSQL persistent

### Pași pentru deployment

#### 1. Push codul pe GitHub
```bash
git add .
git commit -m "Configure PostgreSQL for production deployment"
git push origin main
```

#### 2. Creează serviciile pe Render.com
1. Mergi la [dashboard.render.com](https://dashboard.render.com)
2. Click "New" → "Blueprint" (pentru a folosi render.yaml)
3. Conectează repository-ul GitHub
4. Render va crea automat:
   - PostgreSQL database
   - Web service pentru backend

#### 3. Configurare manuală (alternativ)
Dacă nu folosești Blueprint:

**Creează PostgreSQL database:**
1. "New" → "PostgreSQL"
2. Nume: `nearby-postgres`
3. Plan: Free
4. Database: `nearby_db`
5. User: `nearby_user`

**Creează Web Service:**
1. "New" → "Web Service"
2. Conectează repository-ul
3. Configurează:
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/nearby-backend-0.0.1-SNAPSHOT.jar`
   - Environment Variables:
     - `SPRING_PROFILES_ACTIVE`: `production`
     - `DATABASE_URL`: URL-ul de la PostgreSQL
     - `DB_USERNAME`: username-ul de la PostgreSQL
     - `DB_PASSWORD`: parola de la PostgreSQL

### URL-ul aplicației
După deployment, aplicația va fi disponibilă la:
`https://nearby-backend.onrender.com` (sau numele pe care îl alegi)

### Verificare deployment
1. Accesează URL-ul aplicației
2. Verifică logs în Render dashboard
3. Testează endpoint-urile API

### Recomandări suplimentare
- Configurează CORS pentru frontend
- Adaugă autentificare/autorizare
- Configurează monitoring și logging
- Adaugă health checks 