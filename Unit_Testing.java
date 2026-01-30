@Test
void testLoginWithInvalidPassword()
{
    AuthRequest request = new AuthRequest("valid_user", "wrong_password");
    ResponseEntity<?> response = controller.login(request);
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
}
