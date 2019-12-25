<#import "elements/environment.ftl" as macros> 
<#import "elements/notice.ftl" as macrosN>

<@macros.environment> 
<@macrosN.notice str="Creating a new project takes about 35 seconds"/>
<@macrosN.notice str="Deleting a project takes about 3 seconds"/>
<form
	action="/gui/projects"
	method="post"
	class="form-inline">
	<div class="form-row mt-5 mb-5">
		<label class="col-sm-5.5 col-form-label">Create new project:</label>
		<div class="col-sm">
			<input 
				type="text"
				name="name"
				class="form-control"
				placeholder="Name"/>
		</div> 
		<div class="col-sm">
			<button
				type="submit"
				class="btn btn-primary">Create</button>
		</div>
	</div>
</form>

<#if projects??>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Project Id </th>
				<th scope="col">Name</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<tbody id="projectsList">
		   <#list projects as project>
			   <tr id="projectRow" data-id="${project?counter}">
			      	<th scope="row">${project?counter}</th>
	      			<td id="id">
	      				<a href="/gui/projects/${project.id?c!}">
	      					<#if project.id?has_content>${project.id?c}<#else>null</#if>
      					</a>
      				</td>
	      			<td id="name">
      					<form 
					  		action="/gui/projects/${project.id?c!}"
							method="post">
							<input type="hidden" name="_method" value="put"/>
							<div class="form-row">
								<div class="col-sm">
									<#if project.name?has_content>
										<input 
											type="text"
											name="newName"
											class="form-control"
											value="${project.name}"/>
									<#else>
										null
									</#if>
								</div>
								<div class="col-sm">
									<button
										type="submit"
										class="btn btn-primary"
										name="rename">Rename project</button>
								</div>
							</div>
						</form>
      				</td>
					<td> 
						<form 
					  		action="/gui/projects/${project.id?c!}"
							method="post">
							<input type="hidden" name="_method" value="delete"/>
							<button
								type="submit"
								class="btn btn-primary">Delete project</button>
						</form>	
					</td>
			    </tr> 
			</#list>
		</tbody>
	 </table>
</#if>
</@macros.environment>