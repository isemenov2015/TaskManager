
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h3>Hi, ${name}!</h3>
	<BR />

	<table class="table table-striped">
		<caption>Task list</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Completed?</th>
				<th><a class="btn btn-primary" href="/list-todos?hideDone=true">Hide
						done</a></th>
				<th><a class="btn btn-primary"
					href="/list-todos?hideDone=false">Show all</a></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>

					<td><fmt:formatDate pattern="dd.MM.yyyy"
							value="${todo.targetDate}" /></td>
					<td>${todo.done}</td>
					<td><a href="/done-todo?id=${todo.id}" class="btn btn-success">Done</a></td>
					<td><a href="/update-todo?id=${todo.id}"
						class="btn btn-success">Update</a></td>
					<td><a href="/delete-todo?id=${todo.id}"
						class="btn btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary" href="/add-todo">Add</a>
</div>
<%@ include file="common/footer.jspf"%>