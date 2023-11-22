CREATE DATABASE [QuanLyThuVien] 
USE [QuanLyThuVien]
Go
ALTER DATABASE [QuanLyThuVien] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyThuVien].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyThuVien] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyThuVien]  SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyThuVien]  SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyThuVien]  SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyThuVien] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyThuVien] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyThuVien] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyThuVien] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyThuVien] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyThuVien] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyThuVien] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyThuVien] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyThuVien] SET QUERY_STORE = OFF
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[KhoSach](
    [maSach] [varchar](5) NOT NULL,
    [tongSoLuong] [int] NULL,
    [soLuongCon] [int] NULL,
    [soLuongSachHong] [int] NULL,
    PRIMARY KEY CLUSTERED ([maSach] ASC)
) ON [PRIMARY];
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThongTinSach](
    [maSach] [varchar](5) NOT NULL,
    [tenSach] [nvarchar](100) NULL,
    [maDMSach] [char](10) NULL,
    [maTheLoai] [char](10) NOT NULL,
    [maTacGia] [nvarchar](100) NOT NULL,
	[tenTacGia] [nvarchar](100) NOT NULL,
    [NXB] [nvarchar](100) NOT NULL,
    [namXuatBan] [int] NULL,
    [giaTienSach] [money] NOT NULL,
	[tinhTrangSach] [nvarchar](100) NULL,
    [tomTatND] [ntext] NULL,

    PRIMARY KEY CLUSTERED ([maSach] ASC)
) ON [PRIMARY];
GO
ALTER TABLE [dbo].[ThongTinSach] WITH CHECK ADD FOREIGN KEY([maSach])
REFERENCES [dbo].[KhoSach] ([maSach])

CREATE TABLE [dbo].[TacGia] (
	[maTacGia] [nvarchar](100) NOT NULL,
	[tenTacGia] [nvarchar](100) NOT NULL,
	[soSach] [int] NOT NULL,
	PRIMARY KEY CLUSTERED 
(
	[maTacGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
ALTER TABLE [dbo].[ThongTinSach] ADD CONSTRAINT [FK__ThongTinS__maTacGia] FOREIGN KEY ([maTacGia]) REFERENCES [dbo].[TacGia] ([maTacGia]);

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuanLy](
	[maQuanLy] [char](10) NOT NULL,
	[matKhau] [varchar](20) NULL,
	[tenQuanLy] [nvarchar](100) NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](3) NULL,
	[diaChi] [nvarchar](100) NULL,
	[sdt] [char](11) NULL,
	[email] [varchar](100) NULL,
	[trangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maQuanLy] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DanhMucSach](
	[maDMSach] [char](10) NOT NULL,
	[tenDMSach] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maDMSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTaiKhoan] [char](10) NOT NULL,
	[matKhau] [varchar](20) NULL,
    [loaiTaiKhoan] [char](10) NULL,
	[tenNguoiDung] [nvarchar](100) NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](3) NULL,
	[email] [varchar](100) NULL,
	[sdt] [char](11) NULL,
	[diaChi] [nvarchar](100) NULL,
	[ngayMoThe] [date] NOT NULL,
    [hanSuDung] [date] NOT NULL, 
	[trangThai] [int] NOT NULL,
	[soLuongMuon] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD CONSTRAINT CK_TaiKhoan_trangThai CHECK (trangThai IN (1, 0));

CREATE TABLE [dbo].[LoaiThe](
    [maLoaiThe] [char](10) NOT NULL,  
    [tenLoaiThe] [nvarchar](100) NOT NULL, 
    [soSachDuocMuon] [int] NOT NULL, 
    [thoiGianDuocMuonToiDa] [int] NOT NULL, 
    [giaTienGiaHan] [money] NOT NULL, 
    PRIMARY KEY CLUSTERED 
    (
        [maLoaiThe] ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD CONSTRAINT FK_LoaiTaiKhoan_maLoaiThe FOREIGN KEY (loaiTaiKhoan) REFERENCES [dbo].[LoaiThe] (maLoaiThe);
GO

DECLARE @SoCuoiHSSV INT;
DECLARE @SoCuoiCBGV INT;
SELECT @SoCuoiHSSV = ISNULL(MAX(CASE WHEN [loaiTaiKhoan] = '3200' THEN CAST(RIGHT([maTaiKhoan], 4) AS INT) END), 0),
       @SoCuoiCBGV = ISNULL(MAX(CASE WHEN [loaiTaiKhoan] = '3000' THEN CAST(RIGHT([maTaiKhoan], 4) AS INT) END), 0)
FROM [dbo].[TaiKhoan];
BEGIN TRAN;

UPDATE [dbo].[TaiKhoan]
SET [maTaiKhoan] = CASE 
    WHEN [loaiTaiKhoan] = '3200' THEN '3121' + RIGHT('0000' + CAST(@SoCuoiHSSV + 1 AS VARCHAR(4)), 4)
    WHEN [loaiTaiKhoan] = '3000' THEN '3120' + RIGHT('0000' + CAST(@SoCuoiCBGV + 1 AS VARCHAR(4)), 4)
    ELSE [maTaiKhoan] 
END
WHERE [loaiTaiKhoan] IN ('3200', '3000');

COMMIT TRAN;

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[CheckSoLuongSach] (@maTaiKhoan CHAR(13), @soLuongSach INT)
RETURNS BIT
AS
BEGIN
    DECLARE @maxSoSachDuocMuon INT

    SELECT @maxSoSachDuocMuon = 
    (
        SELECT TOP 1 LT.soSachDuocMuon
        FROM [dbo].[LoaiThe] LT
        INNER JOIN [dbo].[TaiKhoan] T ON LT.maLoaiThe = T.maTaiKhoan
        WHERE T.maTaiKhoan = @maTaiKhoan
    )

    RETURN CASE WHEN @soLuongSach <= @maxSoSachDuocMuon THEN 1 ELSE 0 END
END;
GO

UPDATE [dbo].[TaiKhoan]
SET trangThai = 1
WHERE maTaiKhoan IN (
    SELECT maTaiKhoan
    FROM [dbo].[LoaiThe]
    WHERE hanSuDung < GETDATE()
);

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanLoaiSach](
    [maTheLoai] [char](10) NOT NULL,
    [tenTheLoai] [nvarchar](100) NOT NULL,
    PRIMARY KEY CLUSTERED 
    (
        [maTheLoai] ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO 
ALTER TABLE [dbo].[ThongTinSach] ADD CONSTRAINT FK_LoaiThe_maTheLoai
FOREIGN KEY (maTheLoai) REFERENCES [dbo].[PhanLoaiSach](maTheLoai);

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuMuon](
	[maPhieuMuon] [varchar](5) NOT NULL,
	[ngayMuon] [date] NULL,
	[soNgayMuon] [int] NULL,
	[hanTraSach] [date] NULL,
	[soLuongSach] [int] NULL,
	[maTaiKhoan] [char](10) NULL,
	[maQuanLy] [char](10) NULL,
	[ghiChu] [nvarchar](max) NULL,
	[trangThai] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuMuon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[PhieuMuon] WITH CHECK ADD FOREIGN KEY([maTaiKhoan]) REFERENCES [dbo].[TaiKhoan] ([maTaiKhoan])
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuMuon](
	[maPhieuMuon] [varchar](5) NOT NULL,
	[maSach] [varchar](5) NOT NULL,
	[ngayThucTra] [date] NULL,
	[tienPhat] [money] NULL,
	[tinhTrangSach] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuMuon] ASC,
    [maSach] ASC
)  WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietPhieuMuon] WITH CHECK ADD FOREIGN KEY([maPhieuMuon])
REFERENCES [dbo].[PhieuMuon] ([maPhieuMUon])
GO

CREATE TABLE [dbo].[PhieuNhapSach](
    [maPhieuNhap] [char](13) NOT NULL,
	[maQuanLy] [char](10) NOT NULL,
    [ngayNhap] [date] NULL,
    [nhaCungCap] [char](10) NULL,
    PRIMARY KEY CLUSTERED
    (
        [maPhieuNhap] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE ChiTietPhieuNhapSach(
    [maPhieuNhap] [char](13) NOT NULL,
    [maSach] [varchar](5) NOT NULL,
    [tenSach] [nvarchar](100) NOT NULL,
    [maTacGia] [nvarchar](100) NOT NULL,
    [maTheLoai] [char](10) NOT NULL,
    [NXB] [nvarchar](100) NOT NULL,
    [namXuatBan] [int] NOT NULL,
    [soLuongNhap] [int] NOT NULL,
	[giaNhap] [money] NULL
    PRIMARY KEY CLUSTERED
    (
        [maPhieuNhap] ASC,
		[maSach] ASC 
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ChiTietPhieuNhapSach] WITH CHECK ADD FOREIGN KEY([maPhieuNhap])
REFERENCES [dbo].[PhieuNhapSach] ([maPhieuNhap])

ALTER TABLE [dbo].[ChiTietPhieuNhapSach]
ADD CONSTRAINT CK_ChiTietPhieuNhapSach_soLuongNhap
CHECK (soLuongNhap >= 0);
CREATE INDEX IX_ChiTietPhieuNhapSach_maSach ON [dbo].[ChiTietPhieuNhapSach] (maSach);
GO 

CREATE PROCEDURE sp_ThemSach
    @maSach varchar(5),
    @tenSach nvarchar(100),
    @maTacGia char(10),
    @maTheLoai char(10),
    @NXB nvarchar(100),
    @namXuatBan int,
    @soLuongNhap int
AS
BEGIN
    INSERT INTO [dbo].[KhoSach] ([maSach], [tongSoLuong]) VALUES (@maSach, @soLuongNhap)
	INSERT INTO [dbo].[ThongTinSach] ([maSach], [tenSach], [maTacGia], [maTheLoai], [NXB], [namXuatBan])VALUES (@maSach, @tenSach, @maTacGia, @maTheLoai, @NXB, @namXuatBan)
    SELECT @maSach AS maSach;
END;
GO

CREATE TABLE [dbo].[ThanhLySach] (
    [maThanhLySach] [varchar](5) NOT NULL,
	[maQuanLy] [char](10) NOT NULL,
    [maSach] [varchar](5) NOT NULL,
    [soLuongSachHong] [int] NULL,
    [lyDoThanhLy] [nvarchar](100) NULL,
    [ngayThanhLy] [date] NULL,
    [ghiChu] [text] NULL,
    [tongTienThanhLy] [money] NOT NULL,
    PRIMARY KEY CLUSTERED (
        [maThanhLySach] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
ALTER TABLE [dbo].[ThanhLySach] WITH CHECK ADD FOREIGN KEY([maSach])
REFERENCES [dbo].[KhoSach] ([maSach])

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[func_nextID](@lastID varchar(5), @prefix varchar(2), @size int)
	returns varchar(5)
as
	BEGIN
		if(@lastID = '')
			set @lastID = @prefix + REPLICATE(0, @size - LEN(@prefix))
		declare @num_nextID int, @nextID varchar(5)
		set @lastID = LTRIM(RTRIM(@lastID))
		set @num_nextID = REPLACE(@lastID, @prefix, '') + 1
		set @size = @size - LEN(@prefix)
		set @nextID = @prefix + RIGHT(REPLICATE(0, @size) + CONVERT(VARCHAR(MAX), @num_nextID), @size)
		return  @nextID
	END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[TinhSoNgayTre](@maPhieuMuon varchar(5))
RETURNS INT
AS
BEGIN
    DECLARE @ngayThucTra date;
    DECLARE @hanTraSach date;
    DECLARE @soNgayTre int;

    SELECT @ngayThucTra = ngayThucTra
    FROM ChiTietPhieuMuon
    WHERE maPhieuMuon = @maPhieuMuon;

    SELECT @hanTraSach = hanTraSach
    FROM PhieuMuon
    WHERE maPhieuMuon = @maPhieuMuon;

    SET @soNgayTre = DATEDIFF(DAY, @hanTraSach, @ngayThucTra);

    RETURN @soNgayTre;
END;
GO

CREATE trigger tr_nextSach on dbo.ThongTinSach
for insert
as
	begin
		declare @lastSach varchar(5)
		set @lastSach = (Select top 1 maSach from ThongTinSach order by maSach desc)
		UPDATE ThongTinSach set maSach = dbo.func_nextID(@lastSach, 'S', 5) where maSach = ''
	end
GO
create trigger tr_nextMaPM on dbo.PhieuMuon
for insert
as
	begin
		declare @lastIdMaPM varchar(5)
		set @lastIdMaPM = (Select top 1 maPhieuMuon from PhieuMuon order by maPhieuMuon desc)
		UPDATE PhieuMuon set maPhieuMuon = dbo.func_nextID(@lastIdMaPM, 'PM', 5) where maPhieuMuon = ''
	end
GO

CREATE PROCEDURE sp_inPhieuMuon
AS
BEGIN
    DECLARE @maPhieuMuon varchar(5)
    DECLARE @ngayMuon date
    DECLARE @soNgayMuon int
    DECLARE @maTaiKhoan char(10)
    DECLARE @maQuanLy char(10)
    DECLARE @ghiChu nvarchar(max)
    DECLARE @trangThai nvarchar(100)

    SELECT @maPhieuMuon = maPhieuMuon,
           @ngayMuon = ngayMuon,
           @soNgayMuon = soNgayMuon,
           @maTaiKhoan = maTaiKhoan,
           @maQuanLy = maQuanLy,
           @ghiChu = ghiChu,
           @trangThai = trangThai
    FROM PhieuMuon
    WHERE maPhieuMuon = (SELECT top 1 maPhieuMuon
                        FROM PhieuMuon
                        ORDER BY maPhieuMuon DESC);
    PRINT 'Mã phiếu mượn: ' + @maPhieuMuon
    PRINT 'Ngày mượn sách: ' + CONVERT(nvarchar(10), @ngayMuon, 103)
    PRINT 'Số ngày mượn: ' + @soNgayMuon
    PRINT 'Mã tài khoản: ' + @maTaiKhoan
    PRINT 'Mã quản lý: ' + @maQuanLy
    PRINT 'Ghi chú: ' + @ghiChu
    PRINT 'Trạng thái: ' + @trangThai
END
GO

CREATE trigger tr_soLuongMuon on ChiTietPhieuMuon 
for insert 
as
	BEGIN
		update TaiKhoan
		set	soluongMuon = soluongMuon + 1
		from TaiKhoan, PhieuMuon, inserted
		where inserted.maPhieuMuon = PhieuMuon.maPhieuMuon and TaiKhoan.maTaiKhoan = PhieuMuon.maTaiKhoan
	END
GO

CREATE trigger tr_soLuongMuon_delete on ChiTietPhieuMuon 
for delete 
as
	BEGIN
		update TaiKhoan
		set	soluongMuon = soluongMuon - 1
		from TaiKhoan, PhieuMuon, inserted
		where inserted.maPhieuMuon = PhieuMuon.maPhieuMuon and TaiKhoan.maTaiKhoan = PhieuMuon.maTaiKhoan
	END
GO

CREATE TRIGGER tr_nextMaPhieuNhap ON dbo.PhieuNhapSach
FOR INSERT
AS
BEGIN
    DECLARE @lastIdMaPhieuNhap char(13)
    SET @lastIdMaPhieuNhap = (SELECT TOP 1 maPhieuNhap FROM PhieuNhapSach ORDER BY maPhieuNhap DESC)

    UPDATE PhieuNhapSach SET maPhieuNhap = dbo.func_nextID(@lastIdMaPhieuNhap, 'PN', 5) WHERE maPhieuNhap = ''
END
go
CREATE TRIGGER tr_updateSoLuongNhapSach ON dbo.ChiTietPhieuNhapSach
FOR INSERT
AS
BEGIN
    DECLARE @maSach varchar(5)
    DECLARE @soLuongNhap int
    SELECT @maSach = i.maSach, @soLuongNhap = i.soLuongNhap
    FROM inserted i
    UPDATE dbo.KhoSach
    SET soLuongCon = soLuongCon + @soLuongNhap
    WHERE maSach = @maSach
END
GO 

INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM001', N'Chuyên ngành Điện-Điện tử')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM002', N'Chuyên ngành Cơ khí')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM003', N'Chuyên ngành Công nghệ thông tin')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM004', N'Chuyên ngành Xây dựng')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM005', N'Sách Tiếng Anh')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM006', N'Kỹ năng sống')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM007', N'Sách nước ngoài')

INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG001', N'Hoàng Thị Mỹ Lệ', 3)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG002', N'Nguyễn Linh Nam', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG003', N'Lê Văn Sung', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG004', N'Nguyễn Đức Lợi', 4)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG005', N'Võ Huy Hoàng', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG006', N'Nguyễn Như Hiền', 4)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG007', N'Nguyễn Đức Trí', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG008', N'Phạm Ngọc Thắng', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG009', N'Đặng Văn Tuệ', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG010', N'Nguyễn Xuân Phú ', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG011', N'Tăng Văn Mùi - Trần Duy Nam', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG012', N'Nguyễn Công Phương', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG013', N'Lưu Bá Thuận', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG014', N'Nguyễn Thái', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG015', N'Bộ Xây dựng', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG016', N'GS.TS.Phạm Văn Hội', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES (N'TG017', N'Phan Quang Minh', 1)

INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL001', N'Giáo trình công nghệ ')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL002', N'Giáo trình kỹ thuật')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL003', N'Ngôn ngữ')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL004', N'Điện - Điện tử')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL005', N'Cơ sở hạ tầng')

INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0003',7,5,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0001',10,7,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0002',25,15,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0004',15,9,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0005',10,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0006',6,3,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0007',8,4,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0008',3,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0009',3,3,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0010',3,0,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0011',23,7,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0012',14,9,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0013',9,5,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0014',32,13,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0015',28,11,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0016',5,1,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0017',4,1,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0018',7,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0019',5,3,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0020',6,6,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0021',10,9,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0022',4,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0023',2,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0024',7,5,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0025',3,2,0)

INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0001', N'Giáo trình kỹ thuật xung số và ứng dụng', N'DM003', N'TL001',N'TG002', N'Nguyễn Linh Nam', N'NXB Công nghệ thông tin', 2016, 125000,N' Bình thường ', N'Với mong muốn chia sẻ kinh nghiệm học lập trình và các kỹ năng mà anh đã trải qua trong suốt quá trình học và làm việc với tư cách là người đi trước cũng như là một Developer Full Stack, nên anh đã quyết định xuất bản sách')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0002', N'Lập trình cơ bản với C', N'DM003', N'TL001',N'TG001', N'Hoàng Thị Mỹ Lệ', N'NXB Công nghệ thông tin', 2016, 110000,N' Bình thường ',   N'Với mong muốn chia sẻ kinh nghiệm học lập trình và các kỹ năng mà anh đã trải qua trong suốt quá trình học và làm việc với tư cách là người đi trước cũng như là một Developer Full Stack, nên anh đã quyết định xuất bản sách')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0003', N'Trường điện từ - Lý thuyết và bài tập', N'DM003', N'TL001',N'TG003', N'Lê Văn Sung', N'NXB Công nghệ thông tin', 2016, 90000,N' Bình thường ',   N'Với mong muốn chia sẻ kinh nghiệm học lập trình và các kỹ năng mà anh đã trải qua trong suốt quá trình học và làm việc với tư cách là người đi trước cũng như là một Developer Full Stack, nên anh đã quyết định xuất bản sách')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0004', N'Cơ sở dữ liệu', N'DM003', N'TL001',N'TG001', N'Hoàng Thị Mỹ Lệ', N'NXB Công nghệ thông tin', 2016, 135000,N' Bình thường ',   N'Với mong muốn chia sẻ kinh nghiệm học lập trình và các kỹ năng mà anh đã trải qua trong suốt quá trình học và làm việc với tư cách là người đi trước cũng như là một Developer Full Stack, nên anh đã quyết định xuất bản sách')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0005', N'Tin học văn phòng', N'DM003', N'TL001',N'TG001', N'Hoàng Thị Mỹ Lệ', N'NXB Công nghệ thông tin', 2016, 73000,N' Bình thường ',   N'Với mong muốn chia sẻ kinh nghiệm học lập trình và các kỹ năng mà anh đã trải qua trong suốt quá trình học và làm việc với tư cách là người đi trước cũng như là một Developer Full Stack, nên anh đã quyết định xuất bản sách')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0006', N'Bơm nhiệt', N'DM002', N'TL002',N'TG004', N'Nguyễn Đức Lợi', N'NXB Cơ Khí', 2018, 95000,N' Bình thường ',  N'Giáo trình gồm có 10 chương, trình bày các vấn đề về cơ cấu máy, phân tích động lực học cơ cấu máy, các vấn đề cơ bản trong thiết kế truyền động cơ khí, thiết kế các bộ truyền cơ khí và bộ phận đỡ nổi các bộ truyền. ')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0007', N'Cơ sở thiết kế máy', N'DM002', N'TL002',N'TG004', N'Nguyễn Đức Lợi', N'NXB Cơ Khí', 2018,100000 ,N' Bình thường ',  N'Giáo trình gồm có 10 chương, trình bày các vấn đề về cơ cấu máy, phân tích động lực học cơ cấu máy, các vấn đề cơ bản trong thiết kế truyền động cơ khí, thiết kế các bộ truyền cơ khí và bộ phận đỡ nổi các bộ truyền. ')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0008', N'Đo lường nhiệt', N'DM002', N'TL002',N'TG005', N'Võ Huy Hoàng', N'NXB Cơ Khí', 2018, 58000,N' Bình thường ',   N'Giáo trình gồm có 10 chương, trình bày các vấn đề về cơ cấu máy, phân tích động lực học cơ cấu máy, các vấn đề cơ bản trong thiết kế truyền động cơ khí, thiết kế các bộ truyền cơ khí và bộ phận đỡ nổi các bộ truyền. ')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0009', N'Nhiên liệu sạch', N'DM002', N'TL002',N'TG004', N'Nguyễn Đức Lợi', N'NXB Cơ Khí', 2018,60000,N' Bình thường ',  N'Giáo trình gồm có 10 chương, trình bày các vấn đề về cơ cấu máy, phân tích động lực học cơ cấu máy, các vấn đề cơ bản trong thiết kế truyền động cơ khí, thiết kế các bộ truyền cơ khí và bộ phận đỡ nổi các bộ truyền. ')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0010', N'Giáo trình kỹ thuật nhiệt', N'DM002', N'TL002',N'TG004', N'Nguyễn Đức Lợi', N'NXB Cơ Khí', 2018, 112000,N' Bình thường ',  N'Giáo trình gồm có 10 chương, trình bày các vấn đề về cơ cấu máy, phân tích động lực học cơ cấu máy, các vấn đề cơ bản trong thiết kế truyền động cơ khí, thiết kế các bộ truyền cơ khí và bộ phận đỡ nổi các bộ truyền. ')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0011', N'Ngoại ngữ 1', N'DM005', N'TL003',N'TG006', N'Nguyễn Như Hiền', N'NXB Ngoại ngữ', 2010, 145000,N' Bình thường ',   N'Nội dung chính của sách, gồm từ mới, mẫu câu được giới thiệu bằng hình ảnh trực quan kết hợp với việc nghe đĩa,kế đến là những bài tập đọc hiểu. Các chủ điểm ngữ pháp được đưa vào sách một cách nhẹ nhàng và tự nhiên thông qua các tình huống thực tế.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0012', N'Grammar in use', N'DM005', N'TL003',N'TG007', N'Nguyễn Đức Trí', N'NXB Ngoại ngữ', 2018, 123000, N' Bình thường ',  N'Nội dung chính của sách, gồm từ mới, mẫu câu được giới thiệu bằng hình ảnh trực quan kết hợp với việc nghe đĩa,kế đến là những bài tập đọc hiểu. Các chủ điểm ngữ pháp được đưa vào sách một cách nhẹ nhàng và tự nhiên thông qua các tình huống thực tế.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0013', N'Listen carefully', N'DM005', N'TL003',N'TG006', N'Nguyễn Như Hiền', N'NXB Ngoại ngữ', 2018, 123000,N' Bình thường ',   N'Nội dung chính của sách, gồm từ mới, mẫu câu được giới thiệu bằng hình ảnh trực quan kết hợp với việc nghe đĩa,kế đến là những bài tập đọc hiểu. Các chủ điểm ngữ pháp được đưa vào sách một cách nhẹ nhàng và tự nhiên thông qua các tình huống thực tế.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0014', N'Ngoại ngữ cơ bản', N'DM005', N'TL003',N'TG006', N'Nguyễn Như Hiền', N'NXB Ngoại ngữ', 2018, 105000,N' Bình thường ',   N'Nội dung chính của sách, gồm từ mới, mẫu câu được giới thiệu bằng hình ảnh trực quan kết hợp với việc nghe đĩa,kế đến là những bài tập đọc hiểu. Các chủ điểm ngữ pháp được đưa vào sách một cách nhẹ nhàng và tự nhiên thông qua các tình huống thực tế.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0015', N'Ngoại ngữ 2', N'DM005', N'TL003',N'TG006', N'Nguyễn Như Hiền', N'NXB Ngoại ngữ', 2018, 145000,N' Bình thường ',   N'Nội dung chính của sách, gồm từ mới, mẫu câu được giới thiệu bằng hình ảnh trực quan kết hợp với việc nghe đĩa,kế đến là những bài tập đọc hiểu. Các chủ điểm ngữ pháp được đưa vào sách một cách nhẹ nhàng và tự nhiên thông qua các tình huống thực tế.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0016', N'Kỹ thuật xử lý tín hiệu điều khiển', N'DM001', N'TL004',N'TG008', N'	Phạm Ngọc Thắng', N'NXB Điện-Điện Tử', 2014, 598000 ,N' Bình thường ',  N'Giáo trình này được sử dụng làm tài liệu học tập cho sinh viên các khối ngành kỹ thuật và các ngành có liên quan đến kỹ thuật.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0017', N'Bài tập vi điều khiển và PLC', N'DM001', N'TL004',N'TG009', N'Đặng Văn Tuệ', N'NXB Điện-Điện Tử', 2014, 78000,N' Bình thường ',   N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0018', N'Khí cụ điện - kết cấu, sử dụng và sửa chữa', N'DM001', N'TL004',N'TG010', N'Nguyễn Xuân Phú', N'NXB Điện-Điện Tử', 2014, 132000,N' Bình thường ',   N'Giáo trình này được sử dụng làm tài liệu học tập cho sinh viên các khối ngành kỹ thuật và các ngành có liên quan đến kỹ thuật.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0019', N'Sổ tay chuyên ngành điện', N'DM001', N'TL004',N'TG011',N'Tăng Văn Mùi - Trần Duy Nam', N'NXB Điện-Điện Tử', 2013, 54000 ,N' Bình thường ', N'Giáo trình này được sử dụng làm tài liệu học tập cho sinh viên các khối ngành kỹ thuật và các ngành có liên quan đến kỹ thuật.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0020', N'Bài tập điều khiển tự động', N'DM001', N'TL004',N'TG012', N'	Nguyễn Công Phương', N'NXB Điện-Điện Tử', 2013,97000,N' Bình thường ',  N'Giáo trình này được sử dụng làm tài liệu học tập cho sinh viên các khối ngành kỹ thuật và các ngành có liên quan đến kỹ thuật.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0021', N'Sổ tay máy làm đất và làm đường', N'DM004', N'TL005',N'TG013', N'Lưu Bá Thuận', N'NXB Xây dựng', 2015, 54000,N' Bình thường ',  N'Cuốn sách này nhằm hệ thống hóa và trang bị các khái niệm, thông tin cơ bản về các hệ thống kỹ thuật trong công trình cho các sinh viên trong trường Đại học Xây dựng nói riêng cũng như các trường đại học có đạo tạo ngành kỹ thuật xây dựng.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0022', N'Móng cọc phân tích và thiết kế', N'DM004', N'TL005',N'TG014', N'Nguyễn Thái', N'NXB Xây dựng', 2014, 92000,N' Bình thường ',   N'Cuốn sách này nhằm hệ thống hóa và trang bị các khái niệm, thông tin cơ bản về các hệ thống kỹ thuật trong công trình cho các sinh viên trong trường Đại học Xây dựng nói riêng cũng như các trường đại học có đạo tạo ngành kỹ thuật xây dựng.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0023', N'Cấu tạo bê tông cốt thép', N'DM004', N'TL005',N'TG015', N'Bộ Xây dựng', N'NXB Xây dựng', 2014, 167000,N' Bình thường ',  N'Cuốn sách này nhằm hệ thống hóa và trang bị các khái niệm, thông tin cơ bản về các hệ thống kỹ thuật trong công trình cho các sinh viên trong trường Đại học Xây dựng nói riêng cũng như các trường đại học có đạo tạo ngành kỹ thuật xây dựng.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0024', N'Kết cấu thép - Công trình đặc biệt', N'DM004', N'TL005',N'TG016', N'GS.TS.Phạm Văn Hội ', N'NXB Xây dựng', 2013, 127000,N' Bình thường ',   N'Cuốn sách này nhằm hệ thống hóa và trang bị các khái niệm, thông tin cơ bản về các hệ thống kỹ thuật trong công trình cho các sinh viên trong trường Đại học Xây dựng nói riêng cũng như các trường đại học có đạo tạo ngành kỹ thuật xây dựng.')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [tenTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0025', N'Kết cấu bê tông cốt thép - Phần cấu kiện cơ bản', N'DM004', N'TL005',N'TG017', N'Phan Quang Minh', N'NXB Xây dựng', 2013, 125000,N' Bình thường ',  N'Cuốn sách này nhằm hệ thống hóa và trang bị các khái niệm, thông tin cơ bản về các hệ thống kỹ thuật trong công trình cho các sinh viên trong trường Đại học Xây dựng nói riêng cũng như các trường đại học có đạo tạo ngành kỹ thuật xây dựng.')

INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'3000', N'Cán Bộ Giảng Viên', 25, 90, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'3200', N'Học Sinh - Sinh Viên',10, 20, 100000)

INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312141001', N'abc1231', N'3200', N'Phạm Văn Tâm', CAST(N'2001-09-02' AS Date), N'Nam', N'phamvantam@gmail.com', N'0776155064', N' 495/18 Tô Hiến Thành, Phường 14 , Quận 10 ',  CAST(N'2023-01-01' AS Date), CAST(N'2023-12-31' AS Date ), 1, 0)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312141002', N'abc123', N'3200', N'Hà Nguyễn Yến Vy', CAST(N'2001-07-09' AS Date), N'Nữ', N'hanguyenyenvy@gmail.com', N'0777443873',N' 336 Trần Xuân Soạn, Tân Hưng , Quận 7' , CAST(N'2023-01-05' AS Date), CAST(N'2023-12-31' AS Date ),1, 2)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312041001', N'abc123', N'3000', N'Nguyễn Bá Sĩ Trâm', CAST(N'2001-07-09' AS Date), N'Nam', N'nguyenbasitram@gmail.com', N'0795841141',N' 793 Trần Xuân Soạn, Tân Hưng , Quận 7 ', CAST(N'2023-01-13' AS Date), CAST(N'2023-12-31' AS Date ), 1, 0)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312141003', N'abc123', N'3200', N'Phan Thị Anh Thư', CAST(N'2001-07-09' AS Date), N'Nữ', N'phanthianhthu@gmail.com', N'0375141345',N' 628/12 Phan Văn Trị ,Phường 10, Gò Vấp', CAST(N'2023-02-03' AS Date), CAST(N'2024-02-03' AS Date ), 1, 0)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312041002', N'abc123', N'3000', N'Nguyễn Thanh Tiến', CAST(N'2001-07-09' AS Date), N'Nam', N'thanhtien123@gmail.com', N'0779997865', N' 235/2 Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh , Quận 1', CAST(N'2023-02-10' AS Date), CAST(N'2024-02-10' AS Date ), 1, 2)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312141004', N'abc123', N'3200', N'Trương Tiến Anh', CAST(N'2001-07-09' AS Date), N'Nam', N'tienanh7723@gmail.com', N'0901345667', N' 1050 Tạ Quang Bửu, Phường 6 , Quận 8', CAST(N'2023-01-20' AS Date), CAST(N'2023-12-31' AS Date ),1, 0)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312141005', N'abc123', N'3200', N'Nguyễn Quang Tuấn Nghĩa', CAST(N'2001-07-09' AS Date), N'Nam', N'tuannghia@gmail.com', N'0366123421',N' 74 Trần Hưng Đạo, Phường 7, Quận 5',  CAST(N'2023-03-18' AS Date), CAST(N'2024-03-30' AS Date ),1, 2)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [ngayMoThe], [hanSuDung], [trangThai], [soLuongMuon]) VALUES (N'312141006', N'abc123', N'3200', N'Trần Hồng Quang', CAST(N'2001-07-09' AS Date), N'Nam', N'hongquang@gmail.com', N'0789901451', N' 1001 Đường Cách Mạng Tháng 8 , Phường 5, Quận Tân Bình', CAST(N'2023-09-05' AS Date), CAST(N'2024-09-30' AS Date ), 1, 2)

INSERT [dbo].[QuanLy] ([maQuanLy], [matKhau], [tenQuanLy], [ngaySinh], [gioiTinh], [diaChi], [sdt], [email], [trangThai]) VALUES (N'101010', N'abc123', N'ADMIN', CAST(N'1990-07-02' AS Date), N'Nam', N'TP.Hồ Chí Minh', N'0773123889', N'admin@gmail.com', 1)
INSERT [dbo].[QuanLy] ([maQuanLy], [matKhau], [tenQuanLy], [ngaySinh], [gioiTinh], [diaChi], [sdt], [email], [trangThai]) VALUES (N'101011', N'abc123', N'THỦ THƯ', CAST(N'1997-02-28' AS Date), N'Nữ', N'TP.Hồ Chí Minh', N'0795841141', N'thuthu@gmail.com', 1)

INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM001', CAST(N'2023-08-10' AS Date), 7, CAST(N'2023-08-17' AS Date), 2 , N'312141002', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM002', CAST(N'2023-08-11' AS Date), 7, CAST(N'2023-08-18' AS Date), 3 , N'312141004', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM003', CAST(N'2023-08-12' AS Date),12, CAST(N'2023-08-24' AS Date), 1 , N'312141006', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM004', CAST(N'2023-08-24' AS Date), 7, CAST(N'2023-08-31' AS Date), 3 , N'312141001', N'101010 ', N'', N'Đã trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM005', CAST(N'2023-08-24' AS Date), 7, CAST(N'2023-08-31' AS Date), 2 , N'312041001', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM006', CAST(N'2023-08-24' AS Date), 7, CAST(N'2023-08-31' AS Date), 3 , N'312141004', N'101010 ', N'', N'Đã trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM007', CAST(N'2023-08-25' AS Date), 7, CAST(N'2023-09-01' AS Date), 2 , N'312141005', N'101010 ', N'Không', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM008', CAST(N'2023-08-20' AS Date), 7, CAST(N'2023-08-17' AS Date), 2 , N'312141006', N'101010 ', N'', N'Chưa trả')

INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM001', N'S0001', CAST(N'2023-08-17' AS Date), 137500.0000, N'Mất')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM001', N'S0002', CAST(N'2022-08-17' AS Date), 5500.0000, N'Hư hỏng mức 1')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM002', N'S0003', CAST(N'2023-08-22' AS Date), 20000.0000, N'Trễ hạn')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM002', N'S0004', CAST(N'2023-08-30' AS Date), 20000.0000, N'Trễ hạn')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM002', N'S0005', CAST(N'2023-08-20' AS Date), 20000.0000, N'Trễ hạn')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM003', N'S0006', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM004', N'S0006', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM004', N'S0008', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM004', N'S0009', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM005', N'S0006', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM005', N'S0008', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM006', N'S0002', CAST(N'2023-08-31' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM006', N'S0003', CAST(N'2023-08-31' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM006', N'S0007', CAST(N'2023-08-31' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM007', N'S0003', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM007', N'S0005', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM008', N'S0007', CAST(N'2023-09-10' AS Date), 240000.0000, N'Trễ hạn')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM008', N'S0010', CAST(N'2023-09-10' AS Date), 240000.0000, N'Trễ hạn')

INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [maQuanLy], [ngayNhap], [nhaCungCap]) VALUES (N'PN001',N'101011', CAST(N'2023-08-01' AS Date), N'NCC001')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [maQuanLy], [ngayNhap], [nhaCungCap]) VALUES (N'PN002',N'101011', CAST(N'2023-08-08' AS Date), N'NCC002')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [maQuanLy], [ngayNhap], [nhaCungCap]) VALUES (N'PN003',N'101011', CAST(N'2023-08-09' AS Date), N'NCC003')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [maQuanLy], [ngayNhap], [nhaCungCap]) VALUES (N'PN004',N'101011', CAST(N'2023-08-10' AS Date), N'NCC004')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [maQuanLy], [ngayNhap], [nhaCungCap]) VALUES (N'PN005',N'101011', CAST(N'2023-08-11' AS Date), N'NCC005')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [maQuanLy], [ngayNhap], [nhaCungCap]) VALUES (N'PN006',N'101011', CAST(N'2023-08-12' AS Date), N'NCC006')

INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES (N'PN001',N'S0025',N'Lập trình Python nâng cao',N'TG001',N'TL001',N'NXB Công nghệ thông tin',2023,10,175000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES (N'PN001',N'S0026',N'Lập trình Java nâng cao',N'TG001',N'TL002',N'NXB Giáo dục',2023,20,20600)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES (N'PN001',N'S0027',N'Lập trình Go nâng cao',N'TG002' ,N'TL003',N'NXB Công nghệ thông tin',2023,15,198000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES (N'PN002',N'S0028',N'Toán cao cấp',N'TG005',N'TL001',N'NXB Giáo dục',2023,25,210000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES (N'PN002',N'S0029',N'Vật lý đại cương',N'TG006',N'TL002',N'NXB Giáo dục',2023,15,176000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES (N'PN002',N'S0030',N'Hóa học đại cương',N'TG012',N'TL003',N'NXB Giáo dục',2023,15, 97000)

INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maQuanLy], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES (N'TL002', N'101011', N'S0001', 2, N'Sách bị cũ', CAST(N'2023-08-13' AS Date) , N'Không có',20000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maQuanLy], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES (N'TL003', N'101011', N'S0002', 3, N'Sách bị mốc', CAST(N'2023-08-14'AS Date), N'Không có',525000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maQuanLy], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES (N'TL004', N'101011', N'S0003', 4, N'Sách bị mất trang', CAST(N'2023-08-15'AS Date), N'Không có',150000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maQuanLy], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES (N'TL005', N'101011', N'S0004', 5, N'Sách bị lỗi in', CAST(N'2023-08-16'AS Date), N'Không có',224000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maQuanLy], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES (N'TL006', N'101011', N'S0005', 6, N'Sách bị cháy', CAST(N'2023-08-17' AS Date), N'Không có',632000)

ALTER TABLE [dbo].[QuanLy] ADD  DEFAULT ('1') FOR [trangThai]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT ('1') FOR [trangThai]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT ((0)) FOR [soLuongMuon]
GO
ALTER TABLE [dbo].[ChiTietPhieuMuon]  WITH CHECK ADD FOREIGN KEY([maSach])
REFERENCES [dbo].[ThongTinSach] ([maSach])
GO
ALTER TABLE [dbo].[PhieuMuon]  WITH CHECK ADD FOREIGN KEY([maQuanLy])
REFERENCES [dbo].[QuanLy] ([maQuanLy])
GO
ALTER TABLE [dbo].[PhieuMuon]  WITH CHECK ADD FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([maTaiKhoan])
GO
ALTER TABLE [dbo].[ThongTinSach]  WITH CHECK ADD FOREIGN KEY([maDMSach])
REFERENCES [dbo].[DanhMucSach] ([maDMSach])
ALTER TABLE [dbo].[QuanLy]  WITH CHECK ADD CHECK  (([Email] like '[a-z]%@%'))
GO
ALTER TABLE [dbo].[QuanLy]  WITH CHECK ADD CHECK  (([SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR [SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD CHECK  (([Email] like '[a-z]%@%'))
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD CHECK  (([SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR [SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[DanhMucSach] ADD  DEFAULT (1) FOR [maDMSach]
GO
ALTER TABLE [dbo].[PhanLoaiSach] ADD  DEFAULT (1) FOR [maTheLoai]
GO
ALTER TABLE [dbo].[DanhMucSach]  WITH CHECK ADD CHECK  (([tenDMSach] IS NOT NULL))
GO
ALTER TABLE [dbo].[PhanLoaiSach]  WITH CHECK ADD CHECK  (([tenTheLoai] IS NOT NULL))
GO
ALTER DATABASE [QuanLyThuVien] SET  READ_WRITE 
GO
