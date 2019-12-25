<#macro notice str="">
<style>
.notice {
	margin-bottom: 30px;
	color: red;
}
</style>
<#if str?has_content> 
	<h6 class="notice">${str}</h6>
<#else> 
	<h6 class="notice">${message!}</h6>		
</#if>
</#macro>  