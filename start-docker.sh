#!/bin/bash

# Script de création de la base de données PostgreSQL pour Suivi Candidature

echo "🚀 Démarrage de la base de données PostgreSQL..."

docker run --name suivi-candidature-db \
  -e POSTGRES_DB=suivie_candidature \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:latest

echo "✅ Base de données PostgreSQL démarrée !"
echo ""
echo "🗄️  Informations de connexion:"
echo "   - Hôte: localhost"
echo "   - Port: 5432"
echo "   - Base: suivie_candidature"
echo "   - User: postgres"
echo "   - Password: postgres"