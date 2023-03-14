<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Etudiants</title>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<h1> Page Etudiants </h1>
		<br />
		
		<form method="POST" action="etudiants">
			<label for="nom">Nom</label> <br />
			<input type="text" id="nom" name="nom" placeholder="Nom"/>
			<br />
			
			<label for="prenom">Prenom</label> <br />
			<input type="text" id="prenom" name="prenom" placeholder="Prenom" />
			<br />
			
			<label for="genre">Genre</label> <br />
			<select id="genre" name="genre">
				<option value="Homme"> Homme </option>
				<option value="Femme"> Femme </option>
				<option value="Autre"> Autre </option>
			</select>
			<br />
			
			<label for="sitePrecedent">Site précédent</label> <br />
			<select id="sitePrecedent" name="sitePrecedent">
				<option value="Angers"> Angers </option>
				<option value="Paris"> Paris </option>
				<option value="Dijon"> Dijon </option>
				<option value="Autre"> Autre </option>
			</select>
			<br />
			
			<label for="formationPrecedente">Formation précédente</label> <br />
			<select id="formationPrecedente" name="formationPrecedente">
				<option value="E3e"> E3e </option>
				<option value="E4e"> E4e </option>
				<option value="Bachelor"> Bachelor </option>
				<option value="Apprenti"> Apprenti </option>
				<option value="Autre"> Autre </option>
			</select>
			<br />
			<br />
			
			<input type="submit" name="ajouterEtudiant" value="Ajouter etudiant"/>
		</form>
		
		<c:if test="${ erreurInsertionEtudiant != null }">
			<br />
			<p style="color:red"> <c:out value="${ erreurInsertionEtudiant }" /></p>
		</c:if>
		
		<br />
		<hr />
		<br />
		
		<form method="POST" action="etudiants" enctype="multipart/form-data">
	        <input type="file" name="fichier" id="fichier" />
	        
	        <br />
	        <br />
			<input type="submit" name="ajouterCsv" value="Ajouter liste etudiant">
			<p style="color:red;"><c:out value="${ erreurImportCsv }"></c:out></p>
		</form>
	</body>
</html>