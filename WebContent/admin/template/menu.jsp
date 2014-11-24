
        <nav class="full-size nav-bg">
        	<div id="menu-wrap">
            	<ul id="navigation">
				<li><a href="${pageContext.request.contextPath}/happ/admin/rooms">Dashboard</a></li>
				<!-- Dashboard, Forgot Password, Change Password -->
				<li><a href="">Manage</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/happ/admin/users">User</a></li> 
						<li><a href="${pageContext.request.contextPath}/happ/admin/rooms">Rooms</a></li>
						<li><a href="${pageContext.request.contextPath}/happ/admin/games">Games</a></li>
						<li><a href="${pageContext.request.contextPath}/happ/admin/redeems">Redeem</a></li>
						<li><a href="${pageContext.request.contextPath}/happ/admin/sendPN">Send PN</a></li> 
					</ul></li>
				<!-- Manage User, Rooms, Games , Send PN, Send Mail, Block User -->
				<li><a href="${pageContext.request.contextPath}/happ/admin/chart/admin_reg_chart">Reports</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/happ/admin/chart/admin_reg_chart">Registration Report</a></li>
						<li><a href="${pageContext.request.contextPath}/happ/admin/chart/admin_tra_chart">Transaction Report</a></li>
					</ul>
				</li>
				<!-- Generate Report, Revenue -> Monthly, Quaterly, Yearly; User Registration -> Monthly , Quaterly, Yearly ; Played Game -> Monthly, Quaterly, Yearly  -->
				<li><a href="#">Settings</a></li>
				<!-- System Setting, KYL Template, Mail Template, Forget Password, Create Role User/Super User/Admin for Admin Site, Restricted and default Navigation Page Setting for Roles -->
				<li><a href="redirect/cntUsQuery">View Query</a></li>
				<!-- View Query or Contact us Request -->
				<li><a href="redirect/kyl" style="background: none">KYL</a></li>
				<!--  View Know Your Luck Request, Send Mail , Send PN -->
			</ul>
	</div>
</nav>